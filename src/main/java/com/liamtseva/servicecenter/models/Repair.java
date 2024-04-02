package main.java.com.liamtseva.servicecenter.models;

import java.util.Objects;
import java.util.UUID;

public class Repair {
  private UUID repairId;
  private UUID customerId;
  private UUID productId;

  public Repair(UUID repairId, UUID customerId, UUID productId) {
    this.repairId = repairId;
    this.customerId = customerId;
    this.productId = productId;
  }

  public Repair() {
    this.repairId = UUID.randomUUID();
  }

  public UUID getRepairId() {
    return repairId;
  }

  public void setRepairId(UUID repairId) {
    this.repairId = repairId;
  }

  public UUID getCustomerId() {
    return customerId;
  }

  public void setCustomerId(UUID customerId) {
    this.customerId = customerId;
  }

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Repair repair = (Repair) o;
    return Objects.equals(repairId, repair.repairId) &&
        Objects.equals(customerId, repair.customerId) &&
        Objects.equals(productId, repair.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(repairId, customerId, productId);
  }
}
