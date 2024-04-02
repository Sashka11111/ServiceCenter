package main.java.com.liamtseva.servicecenter.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import main.java.com.liamtseva.servicecenter.models.Customer;

public class CustomerDao {
  private Connection connect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:src/main/resoursec/db/ServiceCenter.db");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }
  public void addCustomer(Customer customer) {
    String sql = "INSERT INTO Customers(customer_id, name, email) VALUES(?, ?, ?)";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, customer.getCustomerId());
      pstmt.setString(2, customer.getName());
      pstmt.setString(3, customer.getEmail());
      pstmt.executeUpdate();
    } catch (SQLException e) {
    System.out.println(e.getMessage());
  }
  }

  public List<Customer> getAllCustomers() {
    List<Customer> customers = new ArrayList<>();
    String sql = "SELECT * FROM Customers";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery())  {
      while (rs.next()) {
        UUID customerId = UUID.fromString(rs.getString("customer_id"));
        String name = rs.getString("name");
        String email = rs.getString("email");
        Customer customer = new Customer(customerId, name, email);
        customers.add(customer);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return customers;
  }
  public Customer getCustomerById(UUID customerId)  {
    String sql = "SELECT * FROM Customers WHERE customer_id = ?";
    Customer customer = null;
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, customerId.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
          String name = rs.getString("name");
          String email = rs.getString("email");
          customer =  new Customer(customerId, name, email);
        }
      }catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    
    return customer;
  }

  public void updateCustomer(Customer customer) throws SQLException {
    String sql = "UPDATE Customers SET name = ?, email = ? WHERE customer_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, customer.getName());
      pstmt.setString(2, customer.getEmail());
      pstmt.setObject(3, customer.getCustomerId());
      pstmt.executeUpdate();
    }catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteCustomer(UUID customerId) throws SQLException {
    String sql = "DELETE FROM Customers WHERE customer_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, customerId.toString());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}