package main.java.com.liamtseva.servicecenter.dao;

import main.java.com.liamtseva.servicecenter.models.RepairNote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepairNoteDao {
  private Connection connect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:src/main/resoursec/db/ServiceCenter.db");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void addRepairNote(RepairNote repairNote) {
    String sql = "INSERT INTO RepairNotes(note_id, repair_id, note) VALUES(?, ?, ?)";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, repairNote.getRepairNoteId());
      pstmt.setObject(2, repairNote.getRepairId());
      pstmt.setString(3, repairNote.getNote());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<RepairNote> getAllRepairNotes() {
    List<RepairNote> repairNotes = new ArrayList<>();
    String sql = "SELECT * FROM RepairNotes";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        UUID noteId = UUID.fromString(rs.getString("note_id"));
        UUID repairId = UUID.fromString(rs.getString("repair_id"));
        String note = rs.getString("note");
        RepairNote repairNote = new RepairNote(noteId, repairId, note);
        repairNotes.add(repairNote);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return repairNotes;
  }

  public RepairNote getRepairNoteById(UUID noteId) {
    String sql = "SELECT * FROM RepairNotes WHERE note_id = ?";
    RepairNote repairNote = null;
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, noteId.toString());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        UUID repairId = UUID.fromString(rs.getString("repair_id"));
        String note = rs.getString("note");
        repairNote = new RepairNote(noteId, repairId, note);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return repairNote;
  }

  public void updateRepairNote(RepairNote repairNote) throws SQLException {
    String sql = "UPDATE RepairNotes SET repair_id = ?, note = ? WHERE note_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, repairNote.getRepairId());
      pstmt.setString(2, repairNote.getNote());
      pstmt.setObject(3, repairNote.getRepairNoteId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteRepairNote(UUID noteId) throws SQLException {
    String sql = "DELETE FROM RepairNotes WHERE note_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, noteId.toString());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
