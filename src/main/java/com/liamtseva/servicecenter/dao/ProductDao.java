package main.java.com.liamtseva.servicecenter.dao;

import main.java.com.liamtseva.servicecenter.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductDao {
  private Connection connect() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:sqlite:src/main/resoursec/db/ServiceCenter.db");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void addProduct(Product product) {
    String sql = "INSERT INTO Products(product_id, name, price) VALUES(?, ?, ?)";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setObject(1, product.getProductId());
      pstmt.setString(2, product.getName());
      pstmt.setInt(3, (int) product.getPrice());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM Products";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery()) {
      while (rs.next()) {
        UUID productId = UUID.fromString(rs.getString("product_id"));
        String name = rs.getString("name");
        int price = rs.getInt("price");
        Product product = new Product(productId, name, price);
        products.add(product);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return products;
  }

  public Product getProductById(UUID productId) {
    String sql = "SELECT * FROM Products WHERE product_id = ?";
    Product product = null;
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, productId.toString());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        String name = rs.getString("name");
        int price = rs.getInt("price");
        product = new Product(productId, name, price);
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return product;
  }

  public void updateProduct(Product product) throws SQLException {
    String sql = "UPDATE Products SET name = ?, price = ? WHERE product_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, product.getName());
      pstmt.setInt(2, (int) product.getPrice());
      pstmt.setObject(3, product.getProductId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void deleteProduct(UUID productId) throws SQLException {
    String sql = "DELETE FROM Products WHERE product_id = ?";
    try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, productId.toString());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
