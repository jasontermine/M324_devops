package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DemoApplicationTests {

    @MockBean
    private TaskRepository taskRepository;

    @Mock
    private TaskService taskService;

    @InjectMocks
    private DemoApplication demoApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTask() {
        // Arrange
        String taskDescription = "Test Task";
        String result = "";

        Task savedTask = new Task();
        savedTask.setTaskdescription(taskDescription);

        when(taskService.saveTask(any(Task.class))).thenReturn(savedTask);

        // Act
        try {
            result = demoApplication.addTask(taskDescription);
        } catch(Exception e) {
            assertEquals(null, e.getMessage());
        }

        // Assert
        assertEquals("Task added successfully", result); // TODO: redirect testen
        // TODO: Erstellten Task kontrollieren
        verify(taskService).saveTask(any(Task.class));
    }

    // TODO : weiteren Test einfügen
}
