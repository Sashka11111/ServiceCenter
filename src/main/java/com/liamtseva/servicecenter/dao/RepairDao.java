package main.java.com.liamtseva.servicecenter.dao;

import main.java.com.liamtseva.servicecenter.models.Repair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepairDao {
  private Connection connect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:src/main/resoursec/db/ServiceCenter.db");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void addRepair(Repair repair) {
    String sql = "INSERT INTO Repairs(repair_id, customer_id, product_id) VALUES(?, ?, ?)";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, repair.getRepairId());
      pstmt.setObject(2, repair.getCustomerId());
      pstmt.setObject(3, repair.getProductId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Repair> getAllRepairs() {
    List<Repair> repairs = new ArrayList<>();
    String sql = "SELECT * FROM Repairs";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        UUID repairId = UUID.fromString(rs.getString("repair_id"));
        UUID customerId = UUID.fromString(rs.getString("customer_id"));
        UUID productId = UUID.fromString(rs.getString("product_id"));
        Repair repair = new Repair(repairId, customerId, productId);
        repairs.add(repair);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return repairs;
  }

  public Repair getRepairById(UUID repairId) {
    String sql = "SELECT * FROM Repairs WHERE repair_id = ?";
    Repair repair = null;
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, repairId.toString());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        UUID customerId = UUID.fromString(rs.getString("customer_id"));
        UUID productId = UUID.fromString(rs.getString("product_id"));
        repair = new Repair(repairId, customerId, productId);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return repair;
  }

  public void updateRepair(Repair repair) throws SQLException {
    String sql = "UPDATE Repairs SET customer_id = ?, product_id = ? WHERE repair_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, repair.getCustomerId());
      pstmt.setObject(2, repair.getProductId());
      pstmt.setObject(3, repair.getRepairId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteRepair(UUID repairId) throws SQLException {
    String sql = "DELETE FROM Repairs WHERE repair_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, repairId.toString());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
