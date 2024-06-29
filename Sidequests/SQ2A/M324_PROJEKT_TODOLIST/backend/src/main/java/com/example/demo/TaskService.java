package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

        public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void deleteTaskByDescription(String description) {
        Task task = (Task) taskRepository.findByName(description);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    public Task findByDescription(String description) {
        return (Task) taskRepository.findByName(description);
    }
}
