package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hauptklasse der Anwendung.
 */
@RestController
@SpringBootApplication
public class DemoApplication {

    /**
     * Startet die Anwendung.
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Das TaskRepository, das für den Zugriff auf die Datenbank verwendet wird.
     */
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Der ObjectMapper, der für die Konvertierung von JSON-Strings in Java-Objekte verwendet wird.
     */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Gibt alle Aufgaben zurück.
     * @return List<Task>
     */
    @CrossOrigin
    @GetMapping("/v1")
    public ResponseEntity<List<Task>> getTasksV1() {
        List<Task> allTasks = new ArrayList<>();
        
        // Add all tasks from the database
        for (Task task : taskRepository.findAll()) {
            allTasks.add(task);
        }

        // Logging task descriptions
        if (!allTasks.isEmpty()) {
            int i = 1;
            for (Task task : allTasks) {
                System.out.println("-task " + (i++) + ":" + task.getTaskDescription());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    @CrossOrigin
    @GetMapping("/v2")
    public ResponseEntity<List<Task>> getTasksV2() {
        List<Task> allTasks = new ArrayList<>();
        
        // Add all tasks from the database
        for (Task task : taskRepository.findAll()) {
            allTasks.add(task);
        }

        // Logging task descriptions
        if (!allTasks.isEmpty()) {
            int i = 1;
            for (Task task : allTasks) {
                System.out.println("-task " + (i++) + ":" + task.getTaskDescription());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(allTasks);
    }

    /**
     * Fügt eine neue Aufgabe hinzu.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @PostMapping("/v1/tasks")
    public ResponseEntity<String> addTaskV1(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            if(taskRepository.findByTaskDescription(taskDescription) != null) {
                // 409 Conflict senden, wenn die Aufgabe bereits existiert
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Task already exists");
            }
            taskRepository.save(newTask);
            return ResponseEntity.status(HttpStatus.CREATED).body("redirect:/");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

        /**
     * Fügt eine neue Aufgabe hinzu.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @PostMapping("/v2/tasks")
    public ResponseEntity<String> addTaskV2(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            if(taskRepository.findByTaskDescription(taskDescription) != null) {
                // 409 Conflict senden, wenn die Aufgabe bereits existiert
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Task already exists");
            }
            taskRepository.save(newTask);
            return ResponseEntity.status(HttpStatus.CREATED).body("redirect:/");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

    /**
     * Löscht eine Aufgabe.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @DeleteMapping("/v1/tasks")
    public ResponseEntity<String> delTaskV1(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task taskToDelete = taskRepository.findByTaskDescription(taskDescription);
            if (taskToDelete != null) {
                taskRepository.delete(taskToDelete);
                return ResponseEntity.status(HttpStatus.OK).body("redirect:/");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }

        /**
     * Löscht eine Aufgabe.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @DeleteMapping("/v2/tasks")
    public ResponseEntity<String> delTaskV2(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task taskToDelete = taskRepository.findByTaskDescription(taskDescription);
            if (taskToDelete != null) {
                taskRepository.delete(taskToDelete);
                return ResponseEntity.status(HttpStatus.OK).body("redirect:/");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
        }
    }
}
