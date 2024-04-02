package main.java.com.liamtseva.servicecenter.models;

import java.util.Objects;
import java.util.UUID;

public class Product {
  private UUID productId;
  private String name;
  private int price;

  public Product(UUID productId, String name, int price) {
    this.productId = productId;
    this.name = name;
    this.price = price;
  }

  public Product() {
    this.productId = UUID.randomUUID();
  }

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Double.compare(product.price, price) == 0 &&
        Objects.equals(productId, product.productId) &&
        Objects.equals(name, product.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId, name, price);
  }
}
