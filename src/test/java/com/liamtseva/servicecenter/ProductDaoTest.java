package test.java.com.liamtseva.servicecenter;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.com.liamtseva.servicecenter.dao.ProductDao;
import main.java.com.liamtseva.servicecenter.models.Product;

public class ProductDaoTest {

  private ProductDao productDao;

  @Before
  public void setUp() throws Exception {
    productDao = new ProductDao();
  }

  @After
  public void tearDown() throws Exception {
    // Clean up after each test if needed
  }

  @Test
  public void addProduct() {
    // Prepare test data
    UUID productId = UUID.randomUUID();
    String name = "Test Product";
    int price = 99;
    Product testProduct = new Product(productId, name, price);

    // Add the product to the database
    productDao.addProduct(testProduct);

    // Retrieve the added product from the database
    Product addedProduct = productDao.getProductById(productId);

    // Check if the product was added successfully
    assertNotNull(addedProduct);
    assertEquals(productId, addedProduct.getProductId());
    assertEquals(name, addedProduct.getName());
    assertEquals(price, addedProduct.getPrice(),0);
  }

  @Test
  public void getAllProducts() {
    // Retrieve all products from the database
    List<Product> products = productDao.getAllProducts();

    // Check if the list is not null and not empty
    assertNotNull(products);
    assertFalse(products.isEmpty());
  }

  @Test
  public void getProductById() {
    // Prepare test data
    UUID productId = UUID.randomUUID();
    String name = "Test Product";
    int price = 99;
    Product testProduct = new Product(productId, name, price);

    // Add the product to the database
    productDao.addProduct(testProduct);

    // Retrieve the product by its ID from the database
    Product retrievedProduct = productDao.getProductById(productId);

    // Check if the retrieved product matches the added product
    assertNotNull(retrievedProduct);
    assertEquals(name, retrievedProduct.getName());
    assertEquals(price, retrievedProduct.getPrice(), 0);
  }

  @Test
  public void updateProduct() {
    // Prepare test data
    UUID productId = UUID.randomUUID();
    String name = "Test Product";
    int price = 99;
    Product testProduct = new Product(productId, name, price);

    // Add the product to the database
    productDao.addProduct(testProduct);

    // Modify the product's data
    testProduct.setName("Updated Product");
    testProduct.setPrice(199);

    // Update the product in the database
    try {
      productDao.updateProduct(testProduct);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // Retrieve the updated product from the database
    Product updatedProduct = productDao.getProductById(productId);

    // Check if the product's data has been successfully updated
    assertEquals("Updated Product", updatedProduct.getName());
    assertEquals(199, updatedProduct.getPrice(),0);
  }

  @Test
  public void deleteProduct() {
    // Prepare test data
    UUID productId = UUID.randomUUID();
    String name = "Test Product";
    int price = 99;
    Product testProduct = new Product(productId, name, price);

    // Add the product to the database
    productDao.addProduct(testProduct);

    // Delete the product from the database
    try {
      productDao.deleteProduct(productId);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // Retrieve the product by its ID from the database
    Product deletedProduct = productDao.getProductById(productId);

    // Check if the product has been successfully deleted
    assertNull(deletedProduct);
  }
}
