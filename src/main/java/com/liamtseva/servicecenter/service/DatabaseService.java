package main.java.com.liamtseva.servicecenter.service;

import main.java.com.liamtseva.servicecenter.dao.CustomerDao;
import main.java.com.liamtseva.servicecenter.dao.ProductDao;
import main.java.com.liamtseva.servicecenter.dao.RepairDao;
import main.java.com.liamtseva.servicecenter.dao.RepairNoteDao;
import main.java.com.liamtseva.servicecenter.models.Customer;
import main.java.com.liamtseva.servicecenter.models.Product;
import main.java.com.liamtseva.servicecenter.models.Repair;
import main.java.com.liamtseva.servicecenter.models.RepairNote;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DatabaseService {

  private final CustomerDao customerDao;
  private final ProductDao productDao;
  private final RepairDao repairDao;
  private final RepairNoteDao repairNoteDao;

  public DatabaseService() {
    this.customerDao = new CustomerDao();
    this.productDao = new ProductDao();
    this.repairDao = new RepairDao();
    this.repairNoteDao = new RepairNoteDao();
  }

  // Customer CRUD operations
  public void addCustomer(UUID customerId, String name, String email) {
    Customer customer = new Customer(customerId, name, email);
    customerDao.addCustomer(customer);
  }

  public List<Customer> getAllCustomers() {
    return customerDao.getAllCustomers();
  }

  public Customer getCustomerById(UUID customerId) {
    return customerDao.getCustomerById(customerId);
  }

  public void updateCustomer(UUID customerId, String name, String email) throws SQLException {
    Customer customer = new Customer(customerId, name, email);
    customerDao.updateCustomer(customer);
  }

  public void deleteCustomer(UUID customerId) throws SQLException {
    customerDao.deleteCustomer(customerId);
  }

  // Product CRUD operations
  public void addProduct(UUID id, String name, int price) {
    Product product = new Product(id, name, price);
    productDao.addProduct(product);
  }

  // Similarly implement methods for other CRUD operations on Product

  // Repair CRUD operations
  public void addRepair(UUID id, UUID customerId, UUID productId) {
    Repair repair = new Repair(id, customerId, productId);
    repairDao.addRepair(repair);
  }

  // Similarly implement methods for other CRUD operations on Repair

  // RepairNote CRUD operations
  public void addRepairNote(UUID id, UUID repairId, String note) {
    RepairNote repairNote = new RepairNote(id, repairId, note);
    repairNoteDao.addRepairNote(repairNote);
  }

  public List<RepairNote> getAllRepairNotes() {
    return repairNoteDao.getAllRepairNotes();
  }

  public RepairNote getRepairNoteById(UUID id) {
    return repairNoteDao.getRepairNoteById(id);
  }

  public void updateRepairNote(UUID id, UUID repairId, String note) throws SQLException {
    RepairNote repairNote = new RepairNote(id, repairId, note);
    repairNoteDao.updateRepairNote(repairNote);
  }

  public void deleteRepairNote(UUID id) throws SQLException {
    repairNoteDao.deleteRepairNote(id);
  }
}
