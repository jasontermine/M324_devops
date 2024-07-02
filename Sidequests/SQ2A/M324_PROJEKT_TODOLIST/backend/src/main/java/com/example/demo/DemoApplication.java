package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    @GetMapping("/")
    public List<Task> getTasks() {
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

        return allTasks;
    }

    /**
     * Fügt eine neue Aufgabe hinzu.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @PostMapping("/tasks")
    public String addTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            if(taskRepository.findByTaskDescription(taskDescription) != null) {
                return "redirect:/"; // Task Duplicate found
            }
            taskRepository.save(newTask);
            return "redirect:/";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * Löscht eine Aufgabe.
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @DeleteMapping("/tasks")
    public String delTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task taskToDelete = taskRepository.findByTaskDescription(taskDescription);
            if (taskToDelete != null) {
                taskRepository.delete(taskToDelete);
                return "redirect:/";
            } else {
                return "error: task not found";
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
