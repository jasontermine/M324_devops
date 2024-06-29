package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void testSaveTask() {
        // Given
        Task task = new Task();
        task.setTaskdescription("Test Task");

        when(taskRepository.save(task)).thenReturn(task);

        // When
        Task savedTask = taskService.saveTask(task);

        // Then
        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTaskdescription());

        verify(taskRepository, times(1)).save(task);
    }
}
