package main.java.com.liamtseva.servicecenter.models;

import java.util.Objects;
import java.util.UUID;

public class RepairNote {
  private UUID repairNoteId;
  private UUID repairId;
  private String note;

  public RepairNote(UUID repairNoteId, UUID repairId, String note) {
    this.repairNoteId = repairNoteId;
    this.repairId = repairId;
    this.note = note;
  }

  public RepairNote() {
    this.repairNoteId = UUID.randomUUID();
  }

  public UUID getRepairNoteId() {
    return repairNoteId;
  }

  public void setRepairNoteId(UUID repairNoteId) {
    this.repairNoteId = repairNoteId;
  }

  public UUID getRepairId() {
    return repairId;
  }

  public void setRepairId(UUID repairId) {
    this.repairId = repairId;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RepairNote that = (RepairNote) o;
    return Objects.equals(repairNoteId, that.repairNoteId) &&
        Objects.equals(repairId, that.repairId) &&
        Objects.equals(note, that.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(repairNoteId, repairId, note);
  }
}