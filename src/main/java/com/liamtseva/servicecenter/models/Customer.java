package main.java.com.liamtseva.servicecenter.models;

import java.util.Objects;
import java.util.UUID;

public class Customer {
  private UUID customerId;
  private String name;
  private String email;

  public Customer(UUID customerId, String name, String email) {
    this.customerId = customerId;
    this.name = name;
    this.email = email;
  }
  public Customer() {
    this.customerId = UUID.randomUUID();
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Customer customer = (Customer) o;
    return Objects.equals(customerId, customer.customerId) &&
        Objects.equals(name, customer.name) &&
        Objects.equals(email, customer.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerId, customerId, email);
  }
}
