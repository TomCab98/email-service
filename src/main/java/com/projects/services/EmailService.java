package com.projects.services;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.projects.models.EmailBody;
import com.projects.models.EmailRequest;
import freemarker.core.HTMLOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class EmailService implements RequestHandler<Map<String, Object>, String> {
  private final MailjetClient client;

  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final String DEFAULT_TEMPLATE_ID = "contact-default";
  private static final Map<String, String> TEMPLATE_MAP = Map.of(
    "contact-default", "contact-default.ftlh",
    "contact-compact", "contact-compact.ftlh"
  );
  private static final Configuration TEMPLATE_CONFIG = buildTemplateConfig();

  public EmailService() {
    this.client = MailjetClientConfig.getInstance();
  }

  @Override
  public String handleRequest(Map<String, Object> event, Context context) {
    EmailRequest request = parseRequest(extractBody(event));

    TransactionalEmail message = TransactionalEmail
      .builder()
      .to(new SendContact(request.getTo()))
      .from(new SendContact(request.getFrom()))
      .subject(request.getSubject())
      .htmlPart(getHtml(request.getBody(), request.getTemplateId()))
      .build();

    SendEmailsRequest emailRequest = SendEmailsRequest
      .builder()
      .message(message)
      .build();

    try {
      emailRequest.sendWith(client);
    } catch (MailjetException e) {
      throw new RuntimeException("Error al enviar email: " + e);
    }

    return "Correctly sent";
  }

  private String getHtml(EmailBody body, String templateId) {
    if (body == null) {
      throw new RuntimeException("El body del email es requerido.");
    }

    String templateFile = resolveTemplateFile(templateId);

    Map<String, Object> data = new HashMap<>();
    data.put("name", body.getName());
    data.put("email", body.getEmail());
    data.put("phone", body.getPhone());
    data.put("message", body.getMessage());

    try (StringWriter writer = new StringWriter()) {
      Template template = TEMPLATE_CONFIG.getTemplate(templateFile);
      template.process(data, writer);
      return writer.toString();
    } catch (IOException | TemplateException e) {
      throw new RuntimeException("Error al renderizar el template: " + templateFile, e);
    }
  }

  private EmailRequest parseRequest(String event) {
    try {
      return objectMapper.readValue(event, EmailRequest.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("error parsing event: " + e);
    }
  }

  private static Configuration buildTemplateConfig() {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    cfg.setClassLoaderForTemplateLoading(EmailService.class.getClassLoader(), "/templates");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
    cfg.setAutoEscapingPolicy(Configuration.ENABLE_IF_SUPPORTED_AUTO_ESCAPING_POLICY);
    return cfg;
  }

  private static String resolveTemplateFile(String templateId) {
    String resolvedId = (templateId == null || templateId.isBlank())
      ? DEFAULT_TEMPLATE_ID
      : templateId;

    String templateFile = TEMPLATE_MAP.get(resolvedId);
    if (templateFile == null) {
      throw new RuntimeException("Template no soportado: " + resolvedId);
    }

    return templateFile;
  }

  private static String extractBody(Map<String, Object> event) {
    if (event == null) {
      throw new RuntimeException("Evento vacío.");
    }

    Object body = event.get("body");
    if (body == null) {
      throw new RuntimeException("El evento no contiene body.");
    }

    return body.toString();
  }
}
