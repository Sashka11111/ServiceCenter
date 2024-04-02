package test.java.com.liamtseva.servicecenter;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.com.liamtseva.servicecenter.dao.RepairNoteDao;
import main.java.com.liamtseva.servicecenter.models.RepairNote;

public class RepairNoteDaoTest {

  private RepairNoteDao repairNoteDao;

  @Before
  public void setUp() throws Exception {
    repairNoteDao = new RepairNoteDao();
  }

  @After
  public void tearDown() throws Exception {
    // Видалення всіх записів після кожного тесту
    List<RepairNote> allRepairNotes = repairNoteDao.getAllRepairNotes();
    for (RepairNote note : allRepairNotes) {
      repairNoteDao.deleteRepairNote(note.getRepairNoteId());
    }
  }

  @Test
  public void addRepairNote() {
    // Підготовка вхідних даних
    UUID repairNoteId = UUID.randomUUID();
    UUID repairId = UUID.randomUUID();
    String noteText = "Test repair note";
    RepairNote repairNote = new RepairNote(repairNoteId, repairId, noteText);

    // Додавання запису про ремонт
    repairNoteDao.addRepairNote(repairNote);

    // Перевірка, чи дійсно було додано запис про ремонт
    RepairNote addedNote = repairNoteDao.getRepairNoteById(repairNoteId);
    assertNotNull(addedNote);
    assertEquals(repairNoteId, addedNote.getRepairNoteId());
    assertEquals(repairId, addedNote.getRepairId());
    assertEquals(noteText, addedNote.getNote());
  }

  @Test
  public void getRepairNoteById() {
    // Додайте запис про ремонт у базу даних для тестування
    UUID noteId = UUID.randomUUID();
    UUID repairId = UUID.randomUUID();
    String noteText = "Test note";
    repairNoteDao.addRepairNote(new RepairNote(noteId, repairId, noteText));

    // Отримайте запис про ремонт за його ідентифікатором
    RepairNote retrievedNote = repairNoteDao.getRepairNoteById(noteId);

    assertNotNull("Retrieved repair note should not be null", retrievedNote);

    // Перевірка інших умов, які ви можете мати
    assertEquals(noteId, retrievedNote.getRepairNoteId());
    assertEquals(repairId, retrievedNote.getRepairId());
    assertEquals(noteText, retrievedNote.getNote());
  }

  @Test
  public void updateRepairNote() throws SQLException {
    // Додайте запис про ремонт у базу даних для тестування
    UUID noteId = UUID.randomUUID();
    UUID repairId = UUID.randomUUID();
    String initialNoteText = "Initial note";
    repairNoteDao.addRepairNote(new RepairNote(noteId, repairId, initialNoteText));

    // Оновіть запис про ремонт
    String updatedNoteText = "Updated note";
    RepairNote updatedNote = new RepairNote(noteId, repairId, updatedNoteText);
    repairNoteDao.updateRepairNote(updatedNote);

    // Отримайте оновлений запис про ремонт з бази даних
    RepairNote retrievedUpdatedNote = repairNoteDao.getRepairNoteById(noteId);

    // Перевірка, чи оновлений запис містить новий текст
    assertEquals(updatedNoteText, retrievedUpdatedNote.getNote());
  }

  @Test
  public void deleteRepairNote() throws SQLException {
    // Додайте запис про ремонт у базу даних для тестування
    UUID noteId = UUID.randomUUID();
    UUID repairId = UUID.randomUUID();
    String noteText = "Test note";
    repairNoteDao.addRepairNote(new RepairNote(noteId, repairId, noteText));

    // Видаліть запис про ремонт
    repairNoteDao.deleteRepairNote(noteId);

    // Перевірка, чи був запис про ремонт видалений шляхом перевірки його наявності в базі даних
    RepairNote deletedNote = repairNoteDao.getRepairNoteById(noteId);
    assertNull(deletedNote);
  }
}
