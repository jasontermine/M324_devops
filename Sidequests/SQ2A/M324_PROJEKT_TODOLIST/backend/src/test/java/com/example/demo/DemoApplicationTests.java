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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;

/**
 * Diese Klasse enthält Tests für die Methoden der DemoApplication-Klasse.
 */
@SpringBootTest(classes = {DemoApplication.class, TestConfig.class})
@ActiveProfiles("test")
class DemoApplicationTests {

    @MockBean
    private TaskRepository taskRepository;

    @InjectMocks
    private DemoApplication demoApplication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Task task = new Task();
        task.setTaskDescription("Test Task");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> {
            Task t = invocation.getArgument(0);
            t.setTaskId(1); // Setze eine Mock-ID
            return t;
        });
        when(taskRepository.findByTaskDescription(any(String.class))).thenAnswer(invocation -> {
            String description = invocation.getArgument(0);
            return tasks.stream().filter(t -> t.getTaskDescription().equals(description)).findFirst().orElse(null);
        });
    }

    @Test
    void testAddTask() {
        String taskDescription = "{\"taskDescription\":\"New Task\"}";
        Task savedTask = new Task();
        savedTask.setTaskDescription("New Task");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
        when(taskRepository.findByTaskDescription("New Task")).thenReturn(null);

        ResponseEntity<String> result = demoApplication.addTask(taskDescription);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).save(any(Task.class));
        assertNotNull(savedTask.getTaskId());
        assertEquals("New Task", savedTask.getTaskDescription());
    }

    @Test
    void testAddTaskConflict() {
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        
        ResponseEntity<String> result = demoApplication.addTask(taskDescription);

        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("Task already exists", result.getBody());
        verify(taskRepository).findByTaskDescription("Test Task");
    }

    @Test
    void testDeleteTask() {
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        Task task = new Task();
        task.setTaskDescription("Test Task");

        when(taskRepository.findByTaskDescription("Test Task")).thenReturn(task);

        ResponseEntity<String> result = demoApplication.delTask(taskDescription);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).findByTaskDescription("Test Task");
        verify(taskRepository).delete(task);
    }

    @Test
    void testDeleteTaskNotFound() {
        String taskDescription = "{\"taskDescription\":\"Nonexistent Task\"}";

        when(taskRepository.findByTaskDescription("Nonexistent Task")).thenReturn(null);

        ResponseEntity<String> result = demoApplication.delTask(taskDescription);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Task not found", result.getBody());
        verify(taskRepository).findByTaskDescription("Nonexistent Task");
    }
}
