package com.projects.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;

public class MailjetClientConfig {
  private static MailjetClient instance;

  private MailjetClientConfig() { }

  public static MailjetClient getInstance() {
    if (instance == null) {
      ClientOptions options = ClientOptions.builder()
        .apiKey(System.getenv("MJ_PUBLIC_KEY"))
        .apiSecretKey(System.getenv("MJ_PRIVATE_KEY"))
        .build();
      instance = new MailjetClient(options);
    }
    return instance;
  }
}
