package com.example.demo.Service;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    public Task createTask(String taskDescription) {
        Task newTask = new Task();
        newTask.setTaskDescription(taskDescription);
        return taskRepository.save(newTask);
    }

    public boolean taskExists(String taskDescription) {
        return taskRepository.findByTaskDescription(taskDescription) != null;
    }

    public Optional<Task> findTaskByDescription(String taskDescription) {
        return Optional.ofNullable(taskRepository.findByTaskDescription(taskDescription));
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
