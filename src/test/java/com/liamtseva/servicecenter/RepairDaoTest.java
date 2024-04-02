package test.java.com.liamtseva.servicecenter;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.com.liamtseva.servicecenter.dao.RepairDao;
import main.java.com.liamtseva.servicecenter.models.Repair;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class RepairDaoTest {
  private RepairDao repairDao;

  @Before
  public void setUp() {
    repairDao = new RepairDao();
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testAddRepair() {
    UUID repairId = UUID.randomUUID();
    UUID customerId = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    Repair repair = new Repair(repairId, customerId, productId);

    // Додавання ремонту через DAO
    repairDao.addRepair(repair);

    // Отримання доданого ремонту з бази даних
    Repair addedRepair = repairDao.getRepairById(repairId);

    // Перевірка, чи був ремонт доданий правильно
    assertNotNull(addedRepair);
    assertEquals(repairId, addedRepair.getRepairId());
    assertEquals(customerId, addedRepair.getCustomerId());
    assertEquals(productId, addedRepair.getProductId());
  }

  @Test
  public void testGetAllRepairs() {
    // Отримання списку всіх ремонтів
    List<Repair> repairs = repairDao.getAllRepairs();

    // Перевірка, чи список не пустий
    assertNotNull(repairs);
    assertFalse(repairs.isEmpty());
  }

  @Test
  public void testGetRepairById() {
    // Підготовка вхідних даних
    UUID repairId = UUID.randomUUID();
    UUID customerId = UUID.randomUUID();
    UUID productId = UUID.randomUUID();

    // Додавання ремонту
    repairDao.addRepair(new Repair(repairId, customerId, productId));

    // Отримання ремонту за його ID
    Repair retrievedRepair = repairDao.getRepairById(repairId);

    // Перевірка, чи ремонт дійсно знайдений
    assertNotNull(retrievedRepair);
    assertEquals(customerId, retrievedRepair.getCustomerId());
    assertEquals(productId, retrievedRepair.getProductId());
  }

  @Test
  public void testUpdateRepair() {
    // Підготовка тестових даних
    UUID repairId = UUID.randomUUID();
    UUID customerId = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    Repair repair = new Repair(repairId, customerId, productId);

    // Додавання ремонту до бази даних
    repairDao.addRepair(repair);

    // Зміна даних ремонту
    UUID newCustomerId = UUID.randomUUID();
    UUID newProductId = UUID.randomUUID();
    repair.setCustomerId(newCustomerId);
    repair.setProductId(newProductId);

    // Оновлення ремонту в базі даних
    try {
      repairDao.updateRepair(repair);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    // Отримання оновленого ремонту з бази даних
    Repair updatedRepair = repairDao.getRepairById(repairId);

    // Перевірка, чи дані ремонту були успішно оновлені
    assertEquals(newCustomerId, updatedRepair.getCustomerId());
    assertEquals(newProductId, updatedRepair.getProductId());
  }

  @Test
  public void testDeleteRepair() throws SQLException {
    // Підготовка вхідних даних
    UUID repairId = UUID.randomUUID();
    UUID customerId = UUID.randomUUID();
    UUID productId = UUID.randomUUID();
    Repair repair = new Repair(repairId, customerId, productId);

    // Додавання ремонту до бази даних
    repairDao.addRepair(repair);

    // Видалення ремонту з бази даних
    repairDao.deleteRepair(repairId);

    // Отримання ремонту за його ID з бази даних
    Repair deletedRepair = repairDao.getRepairById(repairId);

    // Перевірка, чи ремонт був успішно видалений
    assertNull(deletedRepair);
  }
}
