package test.java.com.liamtseva.servicecenter;

import static org.junit.Assert.*;

import java.sql.SQLException;
import main.java.com.liamtseva.servicecenter.dao.CustomerDao;
import main.java.com.liamtseva.servicecenter.models.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

public class CustomerDaoTest {
  private CustomerDao customerDao;

  @Before
  public void setUp() throws Exception {
    customerDao = new CustomerDao();
  }

  @Test
  public void addCustomer() {
    // Підготовка вхідних даних
    UUID customerId = UUID.randomUUID();
    String name = "John Doe";
    String email = "john@example.com";

    // Додавання клієнта
    Customer testCustomer= new Customer(customerId, name, email);
    customerDao.addCustomer(testCustomer);
    // Перевірка, чи клієнт дійсно доданий
    Customer addedCustomer = customerDao.getCustomerById(customerId);
    assertEquals(testCustomer, addedCustomer);
  }

  @Test
  public void getAllCustomers() {
    // Отримання списку всіх клієнтів
    List<Customer> customers = customerDao.getAllCustomers();

    // Перевірка, чи список не пустий
    assertNotNull(customers);
    assertFalse(customers.isEmpty());
  }

  @Test
  public void getCustomerById() {
    // Підготовка вхідних даних
    UUID customerId = UUID.randomUUID();
    String name = "Jane Smith";
    String email = "jane@example.com";

    // Додавання клієнта
    customerDao.addCustomer(new Customer(customerId, name, email));

    // Отримання клієнта за його ID
    Customer retrievedCustomer = customerDao.getCustomerById(customerId);

    // Перевірка, чи клієнт дійсно знайдений
    assertNotNull(retrievedCustomer);
    assertEquals(name, retrievedCustomer.getName());
    assertEquals(email, retrievedCustomer.getEmail());
  }

  @Test
  public void updateCustomer() throws SQLException {
      // Prepare test data
      UUID customerId = UUID.randomUUID();
      String name = "Bob Brown";
      String email = "bob@example.com";
      Customer testCustomer = new Customer(customerId, name, email);

      // Add the customer to the database
      customerDao.addCustomer(testCustomer);

      // Modify the customer's data
      testCustomer.setName("John Smith");
      testCustomer.setEmail("john@example.com");

      // Update the customer in the database
      customerDao.updateCustomer(testCustomer);

      // Retrieve the updated customer from the database
      Customer updatedCustomer = customerDao.getCustomerById(customerId);

      // Check if the customer's data has been successfully updated
      assertEquals("John Smith", updatedCustomer.getName());
      assertEquals("john@example.com", updatedCustomer.getEmail());

  }

  @Test
  public void deleteCustomer() throws SQLException {
    // Підготовка вхідних даних
    UUID customerId = UUID.randomUUID();
    String name = "Bob Brown";
    String email = "bob@example.com";
    Customer testCustomer = new Customer(customerId, name, email);

    // Add the animal to the database
    customerDao.addCustomer(testCustomer);

    // Delete the animal from the database
    customerDao.deleteCustomer(customerId);

    // Retrieve the animal by its id from the database
    Customer deleteCustomer = customerDao.getCustomerById(customerId);

    // Check if the animal has been successfully deleted
    assertNull(deleteCustomer);
  }
}
