package com.example.demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private TaskService taskService;

    private List<Task> tasks = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin
    @GetMapping("/")
    public List<Task> getTasks() {
        System.out.println("API EP '/' returns task-list.");
        List<Task> allTasks = new ArrayList<>(tasks);
        allTasks.addAll(taskService.getAllTasks()); // Add all tasks from the database
        if (!allTasks.isEmpty()) {
            int i = 1;
            for (Task task : allTasks) {
                System.out.println("-task " + (i++) + ":" + task.getTaskdescription());
            }
        }
        return allTasks; // actual task list (internally converted to a JSON stream)
    }

    @CrossOrigin
    @PostMapping("/tasks")
    public String addTask(@RequestBody String taskDescriptionJson) {
        System.out.println("API EP '/tasks': '" + taskDescriptionJson + "'");
        try {
            Task task = mapper.readValue(taskDescriptionJson, Task.class);
            for (Task t : tasks) {
                if (t.getTaskdescription().equals(task.getTaskdescription())) {
                    System.out.println(">>>task: '" + task.getTaskdescription() + "' already exists!");
                    return "redirect:/"; // duplicates will be ignored
                }
            }
            System.out.println("...adding task: '" + task.getTaskdescription() + "'");
            tasks.add(task);
            taskService.saveTask(task); // Persist the task
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @CrossOrigin
    @PostMapping("/delete")
    public String delTask(@RequestBody String taskDescriptionJson) {
        System.out.println("API EP '/delete': '" + taskDescriptionJson + "'");
        try {
            Task task = mapper.readValue(taskDescriptionJson, Task.class);
            Task existingTask = taskService.findByDescription(task.getTaskdescription());
            System.out.println("existingTask: " + (existingTask != null ? existingTask.getTaskdescription() : "null"));
            if (existingTask != null) {
                taskService.deleteTaskByDescription(task.getTaskdescription());
                Iterator<Task> it = tasks.iterator();
                while (it.hasNext()) {
                    Task t = it.next();
                    if (t.getTaskdescription().equals(task.getTaskdescription())) {
                        System.out.println("...deleting task: '" + task.getTaskdescription() + "'");
                        it.remove();
                        return "redirect:/";
                    }
                }
                System.out.println("taskService.deleteTaskByDescription called"); // Logging for verification
            }
            System.out.println(">>>task: '" + task.getTaskdescription() + "' not found!");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
