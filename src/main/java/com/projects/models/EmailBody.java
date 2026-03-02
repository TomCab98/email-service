package com.projects.models;

public class EmailBody {
  private String name;
  private String email;
  private int phone;
  private String message;

  public EmailBody() {}

  public EmailBody(
    String name,
    String email,
    int phone,
    String message
  ) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.message = message;
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public int getPhone() { return phone; }
  public void setPhone(int phone) { this.phone = phone; }

  public String getMessage() { return message; }
  public void setMessage(String message) { this.message = message; }
}
