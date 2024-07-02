package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;

/**
 * Diese Klasse enthält Tests für die Methoden der DemoApplication-Klasse.
 */
@SpringBootTest
class DemoApplicationTests {

    /**
     * Mock des TaskRepository-Objekts, das von der DemoApplication-Klasse verwendet wird.
     */
    @MockBean
    private TaskRepository taskRepository;

    /**
     * Das zu testende DemoApplication-Objekt.
     */
    @InjectMocks
    private DemoApplication demoApplication;

    /**
     * Diese Methode wird vor jedem Test aufgerufen und initialisiert die Mock-Objekte.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Fügen Sie einen Task zur lokalen Liste hinzu, um die Bedingung zu erfüllen
        Task task = new Task();
        task.setTaskDescription("Test Task");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
    }

    /**
     * Testet die getTasks-Methode der DemoApplication-Klasse.
     */
    @Test
    void testAddTask() {
      // Arrange
      String taskDescription = "{\"taskDescription\":\"Test Task\"}";

      Task savedTask = new Task();
      savedTask.setTaskDescription("Test Task");

      when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

      // Act
      String result = demoApplication.addTask(taskDescription);

      // Assert
      assertEquals("redirect:/", result); // Überprüft, ob die Methode die erwartete Weiterleitung zurückgibt
      verify(taskRepository).save(any(Task.class)); // Stellt sicher, dass die save-Methode des Repositories aufgerufen wird
      assertNotNull(savedTask.getTaskId()); // Überprüft, ob die ID des gespeicherten Tasks nicht null ist
      assertEquals("Test Task", savedTask.getTaskDescription()); // Überprüft, ob die Beschreibung des gespeicherten Tasks korrekt ist
    }

    /**
     * Testet die getTasks-Methode der DemoApplication-Klasse.
     */
    @Test
    void testDeleteTask() {
        // Arrange
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        Task task = new Task();
        task.setTaskDescription("Test Task");

        when(taskRepository.findByTaskDescription("Test Task")).thenReturn(task);

        // Act
        String result = demoApplication.delTask(taskDescription);

        // Assert
        assertEquals("redirect:/", result);
        verify(taskRepository).findByTaskDescription("Test Task");
        verify(taskRepository).delete(task);
    }
}
