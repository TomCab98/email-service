package com.projects.models;

public class EmailRequest {
  private String to;
  private String from;
  private String subject;
  private String templateId;
  private EmailBody body;

  public EmailRequest() {}

  public EmailRequest(
    String to,
    String from,
    String subject,
    EmailBody body
  ) {
    this.to = to;
    this.from = from;
    this.subject = subject;
    this.body = body;
  }

  public EmailRequest(
    String to,
    String from,
    String subject,
    String templateId,
    EmailBody body
  ) {
    this.to = to;
    this.from = from;
    this.subject = subject;
    this.templateId = templateId;
    this.body = body;
  }

  public String getTo() { return to; }
  public void setTo(String to) { this.to = to; }

  public String getSubject() { return subject; }
  public void setSubject(String subject) { this.subject = subject; }

  public String getFrom() { return from; }
  public void setFrom(String from) { this.from = from; }

  public String getTemplateId() { return templateId; }
  public void setTemplateId(String templateId) { this.templateId = templateId; }

  public EmailBody getBody() { return body; }
  public void setBody(EmailBody body) { this.body = body; }
}
