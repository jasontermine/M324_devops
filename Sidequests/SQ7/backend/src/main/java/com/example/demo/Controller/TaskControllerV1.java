package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Task;
import com.example.demo.Service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TaskControllerV1 {

    @Autowired
    private TaskService taskService;

    private ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> allTasks = taskService.getAllTasks();

        if (!allTasks.isEmpty()) {
            int i = 1;
            for (Task task : allTasks) {
                System.out.println("-task " + (i++) + ":" + task.getTaskDescription());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    @CrossOrigin
    @PostMapping("/tasks")
    public ResponseEntity<String> addTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();

            if (taskService.taskExists(taskDescription)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Task already exists");
            }

            taskService.createTask(taskDescription);
            return ResponseEntity.status(HttpStatus.CREATED).body("redirect:/");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    @CrossOrigin
    @DeleteMapping("/tasks")
    public ResponseEntity<String> delTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();

            return taskService.findTaskByDescription(taskDescription)
                    .map(task -> {
                        taskService.deleteTask(task);
                        return ResponseEntity.status(HttpStatus.OK).body("redirect:/");
                    })
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
}
