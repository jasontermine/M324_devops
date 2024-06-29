package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

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
        // Fügen Sie einen Task zur lokalen Liste hinzu, um die Bedingung zu erfüllen
        Task task = new Task();
        task.setTaskdescription("Test Task");
        List<Task> tasks = demoApplication.getTasks();
        tasks.add(task);
    }

    @Test
    void testAddTask() {
        // Arrange
        String taskDescription = "{\"taskdescription\":\"Test Task\"}";

        Task savedTask = new Task();
        savedTask.setTaskdescription("Test Task");

        when(taskService.saveTask(any(Task.class))).thenReturn(savedTask);

        // Act
        String result = demoApplication.addTask(taskDescription);

        // Assert
        assertEquals("redirect:/", result);
        verify(taskService).saveTask(any(Task.class));
    }

    @Test
    void testDeleteTask() {
        // Arrange
        String taskDescription = "{\"taskdescription\":\"Test Task\"}";
        Task task = new Task();
        task.setTaskdescription("Test Task");

        when(taskService.findByDescription("Test Task")).thenReturn(task);

        // Act
        String result = demoApplication.delTask(taskDescription);

        // Assert
        assertEquals("redirect:/", result);
        verify(taskService).findByDescription("Test Task");
        verify(taskService).deleteTaskByDescription("Test Task");
    }
}
