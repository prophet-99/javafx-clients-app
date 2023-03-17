package org.prophet99.clientsapp.data.models.entities;

import java.time.LocalDateTime;

public record Client (
  String code,
  String firstName,
  String lastName,
  String email,
  String phone,
  LocalDateTime registrationDate
){
  public Client(String code, String firstName, String lastName, String email, String phone) {
    this(code, firstName, lastName, email, phone, null);
  }

  @Override
  public String toString() {
    return "Client{" +
            "code='" + code + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", registrationDate=" + registrationDate +
            '}';
  }
}
