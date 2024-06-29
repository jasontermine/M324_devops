package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Mock
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

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
        result = demoApplication.addTask(taskDescription);

        // Assert
        assertEquals("Task added successfully", result);
        verify(taskService).saveTask(any(Task.class));
    }
}
