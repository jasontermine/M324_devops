# API-Versionierung

## Inhaltsverzeichnis
- [Verschiedene Versionierungsstrategien](#verschiedene-versionierungsstrategien)
  - [URL-Parameter](#url-parameter)
  - [Request Parameter (Header)](#request-parameter-header)
  - [Custom Header](#custom-header)
  - [Parameter Versionierung](#parameter-versionierung)
- [Bewertung der verschiedenen Methoden](#bewertung-der-verschiedenen-methoden)
- [Entscheidung für eine API-Versionierungsstrategie](#entscheidung-für-eine-api-versionierungsstrategie)
- [Schritt für Schritt Anleitung zur Implementierung der Custom Header Methode](#schritt-für-schritt-anleitung-zur-implementierung-der-custom-header-methode)
  - [Backend](#backend)
  - [Frontend](#frontend)
  - [Swagger-UI](#swagger-ui)

## Verschiedene Versionierungsstrategien

### URL-Parameter
Die einfachste Variante der Versionierung ist die Verwendung von URL-Parametern. Hierbei wird die Version der API in der URL angegeben. Zum Beispiel: `https://api.example.com/v1/resource`.

#### Vorteile:
- Einfach zu implementieren
- Einfach zu testen
- Klar und leicht verständlich für Nutzer

#### Nachteile:
- Kann zu langen URLs führen
- Erfordert Anpassungen an der Routing-Logik
- Kann die URL-Struktur unübersichtlich machen

### Request Parameter (Header)
Eine weitere Möglichkeit ist die Verwendung von Request-Parametern. Hierbei wird die Version der API in einem Header angegeben. Zum Beispiel: `Accept: application/com.example.v1+json`.

#### Vorteile:
- Saubere URL-Struktur
- Ermöglicht flexible Versionierung
- Keine Auswirkungen auf die URL-Struktur

#### Nachteile:
- Komplexer zu implementieren
- Erfordert zusätzliche Konfiguration auf Client-Seite
- Kann zu Problemen mit Caching und Proxies führen

### Custom Header
Eine weitere Möglichkeit ist die Verwendung eines benutzerdefinierten Headers. Hierbei wird die Version der API in einem benutzerdefinierten Header angegeben. Zum Beispiel: `X-API-Version: 1`.

#### Vorteile:
- Flexibel
- Keine Änderungen an der URL-Struktur notwendig
- Unterstützt mehrere Versionierungsstrategien parallel

#### Nachteile:
- Komplexer zu implementieren
- Erfordert zusätzliche Konfiguration auf Client-Seite
- Kann zu Problemen mit Caching und Proxies führen

### Parameter Versionierung
Eine weitere Möglichkeit ist die Verwendung von Parametern in der URL. Hierbei wird die Version der API in der URL angegeben. Zum Beispiel: `https://api.example.com/resource?version=1`.

#### Vorteile:
- Einfach zu implementieren
- Flexibel
- Keine Änderungen an der URL-Struktur notwendig

#### Nachteile:
- Kann zu Missverständnissen führen, wenn Parameter nicht korrekt angegeben werden
- Mögliche Konflikte mit anderen URL-Parametern
- Erfordert zusätzliche Logik zur Versionierung in der Anwendung

## Bewertung der verschiedenen Methoden

| Methode                 | Zuverlässigkeit | Einfachheit              | Flexibilität                |
|-------------------------|-----------------|--------------------------|-----------------------------|
| **URL-Parameter**       | Hoch            | Sehr einfach             | Mittel                      |
| - Vorteile              | - Klar und verständlich für Nutzer | - Einfach zu implementieren | -                          |
| - Nachteile             | - Kann zu langen URLs führen       | - Erfordert Anpassungen an der Routing-Logik | - Kann die URL-Struktur unübersichtlich machen |
| **Request Parameter (Header)** | Hoch   | Mittel                   | Hoch                        |
| - Vorteile              | - Saubere URL-Struktur             | - Ermöglicht flexible Versionierung | - Keine Auswirkungen auf die URL-Struktur |
| - Nachteile             | - Kann zu Problemen mit Caching und Proxies führen | - Komplexer zu implementieren | - Erfordert zusätzliche Konfiguration auf Client-Seite |
| **Custom Header**       | Hoch            | Mittel                   | Hoch                        |
| - Vorteile              | - Flexibel                         | - Keine Änderungen an der URL-Struktur notwendig | - Unterstützt mehrere Versionierungsstrategien parallel |
| - Nachteile             | - Kann zu Problemen mit Caching und Proxies führen | - Komplexer zu implementieren | - Erfordert zusätzliche Konfiguration auf Client-Seite |
| **Parameter Versionierung** | Hoch   | Einfach                  | Mittel                      |
| - Vorteile              | - Einfach zu implementieren         | - Flexibel                    | - Keine Änderungen an der URL-Struktur notwendig |
| - Nachteile             | - Kann zu Missverständnissen führen, wenn Parameter nicht korrekt angegeben werden | - Mögliche Konflikte mit anderen URL-Parametern | - Erfordert zusätzliche Logik zur Versionierung in der Anwendung |


## Entscheidung für eine API-Versionierungsstrategie
Am einfachsten und zuverlässigsten ist die URL-Parameter Methode. Sie ist einfach zu implementieren, leicht zu testen und klar und verständlich für Nutzer. Die Nachteile wie lange URLs oder unübersichtliche URL-Struktur sind in den meisten Fällen vernachlässigbar. Daher haben wir uns für die URL-Parameter Methode entschieden.

## Schritt für Schritt Anleitung zur Implementierung der Custom Header Methode

### Backend
1. Weiteres Dependency ins `pom.xml` hinzufügen. Hiermit wird die Swagger-UI für die API-Dokumentation hinzugefügt, welche die verschiedenen Versionen der API anzeigt.

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

2. Die bestehenden Endpoints aus der `DemoApplication.java` ausgebaut. Dies ist notwendig, um die verschiedenen Versionen der API zu trennen. Allenfalls wird dieses Datei sehr gross und unübersichtlich.

**DemoApplication.java**
```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hauptklasse der Anwendung.
 */
@SpringBootApplication
public class DemoApplication {

    /**
     * Startet die Anwendung.
     * 
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

3. Neue Controller für die verschiedenen Versionen der API erstellen. Hiermit sind die Endpoints übersichtlich mit einer klaren Struktur getrennt.
**TaskControllerV1.java**
```java
package com.example.demo.Controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TaskControllerV1 {

    @Autowired
    private TaskRepository taskRepository;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Gibt alle Aufgaben zurück.
     * 
     * @return List<Task>
     */
    @CrossOrigin
    @GetMapping("/v1")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> allTasks = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            allTasks.add(task);
        }

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
     * 
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @PostMapping("/v1/tasks")
    public ResponseEntity<String> addTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            if (taskRepository.findByTaskDescription(taskDescription) != null) {
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
     * 
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @DeleteMapping("/v1/tasks")
    public ResponseEntity<String> delTask(@RequestBody String taskDescriptionJson) {
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
```

**TaskControllerV2.java**
```java
package com.example.demo.Controller.v2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TaskControllerV2 {

    @Autowired
    private TaskRepository taskRepository;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Gibt alle Aufgaben zurück.
     * 
     * @return List<Task>
     */
    @CrossOrigin
    @GetMapping("/v2")
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> allTasks = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            allTasks.add(task);
        }

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
     * 
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @PostMapping("/v2/tasks")
    public ResponseEntity<String> addTask(@RequestBody String taskDescriptionJson) {
        try {
            JsonNode jsonNode = mapper.readTree(taskDescriptionJson);
            String taskDescription = jsonNode.get("taskDescription").asText();
            Task newTask = new Task();
            newTask.setTaskDescription(taskDescription);
            if (taskRepository.findByTaskDescription(taskDescription) != null) {
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
     * 
     * @param taskDescriptionJson
     * @return String
     */
    @CrossOrigin
    @DeleteMapping("/v2/tasks")
    public ResponseEntity<String> delTask(@RequestBody String taskDescriptionJson) {
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
```

4. Anpassen der Tests, um die neuen Controller zu testen. Da die alten Tests auf die DemoApplication.java verweisen, müssen diese angepasst werden. Hierzu wurden 2 neue Testklassen erstellt, die die neuen Controller testen.

**TaskControllerV1Test.java**
```java
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

import com.example.demo.Controller.v1.TaskControllerV1;
import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;

/**
 * Diese Klasse enthält Tests für die Methoden der DemoApplication-Klasse.
 */
@SpringBootTest(classes = {DemoApplication.class, TestConfig.class})
@ActiveProfiles("test")
class TaskControllerV1Test {

    @MockBean
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskControllerV1 taskControllerV1;

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

        ResponseEntity<String> result = taskControllerV1.addTask(taskDescription);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).save(any(Task.class));
        assertNotNull(savedTask.getTaskId());
        assertEquals("New Task", savedTask.getTaskDescription());
    }

    @Test
    void testAddTaskConflict() {
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        
        ResponseEntity<String> result = taskControllerV1.addTask(taskDescription);

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

        ResponseEntity<String> result = taskControllerV1.delTask(taskDescription);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).findByTaskDescription("Test Task");
        verify(taskRepository).delete(task);
    }

    @Test
    void testDeleteTaskNotFound() {
        String taskDescription = "{\"taskDescription\":\"Nonexistent Task\"}";

        when(taskRepository.findByTaskDescription("Nonexistent Task")).thenReturn(null);

        ResponseEntity<String> result = taskControllerV1.delTask(taskDescription);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Task not found", result.getBody());
        verify(taskRepository).findByTaskDescription("Nonexistent Task");
    }
}
```

**TaskControllerV2Test.java**
```java
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

import com.example.demo.Controller.v2.TaskControllerV2;
import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;

/**
 * Diese Klasse enthält Tests für die Methoden der DemoApplication-Klasse.
 */
@SpringBootTest(classes = {DemoApplication.class, TestConfig.class})
@ActiveProfiles("test")
class TaskControllerV2Test {

    @MockBean
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskControllerV2 taskControllerV2;

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

        ResponseEntity<String> result = taskControllerV2.addTask(taskDescription);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).save(any(Task.class));
        assertNotNull(savedTask.getTaskId());
        assertEquals("New Task", savedTask.getTaskDescription());
    }

    @Test
    void testAddTaskConflict() {
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        
        ResponseEntity<String> result = taskControllerV2.addTask(taskDescription);

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

        ResponseEntity<String> result = taskControllerV2.delTask(taskDescription);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("redirect:/", result.getBody());
        verify(taskRepository).findByTaskDescription("Test Task");
        verify(taskRepository).delete(task);
    }

    @Test
    void testDeleteTaskNotFound() {
        String taskDescription = "{\"taskDescription\":\"Nonexistent Task\"}";

        when(taskRepository.findByTaskDescription("Nonexistent Task")).thenReturn(null);

        ResponseEntity<String> result = taskControllerV2.delTask(taskDescription);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Task not found", result.getBody());
        verify(taskRepository).findByTaskDescription("Nonexistent Task");
    }
}
```

5. Anpassen der `Task.java` und `TaskRepository.java` um die Annotation `@Hidden` hinzuzufügen. Dies versteckt die Entitäten in der Swagger-UI, um die verschiedenen Versionen der API zu trennen. Allenfalls werden ungewünschte Entitäten angezeigt, die nicht in der API angezeigt werden sollen. Das Hinzufügen der Annotation `@Hidden` versteckt die Entitäten in der Swagger-UI.

**Task.java**
```java
package com.example.demo.Model;

import io.micrometer.common.lang.NonNull;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representiert eine Aufgabe, die in der Todo-Liste angezeigt wird.
 
 */
@Entity
@Hidden
@Table(name = "Task")
public class Task {

	/**
	 * Die ID der Aufgabe.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int taskId;

	/**
	 * Die Beschreibung der Aufgabe.
	 */
	@NonNull
	private String taskDescription; // must have the EXACT name as his React state property and may not be ignored!

	/**
	 * Standardkonstruktor.
	 */
	public Task(){
		
	}

	/**
	 * Konstruktor mit Beschreibung.
	 * @param description
	 */
	public Task(String description){
		this.taskDescription = description;
	}

	/**
	 * Konstruktor mit ID und Beschreibung.
	 * @param id
	 * @param description
	 */
	public Task(int id, String description){
		this.taskId = id;
		this.taskDescription = description;
	}

	/**
	 * Gibt die ID der Aufgabe zurück.
	 * @return
	 */
	public int getTaskId(){
		return this.taskId;
	}

	/**
	 * Gibt die Beschreibung der Aufgabe zurück.
	 * @return
	 */
	public String getTaskDescription(){
		return this.taskDescription;
	}

	/**
	 * Setzt die ID der Aufgabe.
	 * @param id
	 */
	public void setTaskId(int id){
		this.taskId = id;
	}

	/**
	 * Setzt die Beschreibung der Aufgabe.
	 * @param description
	 */
	public void setTaskDescription(String description){
		this.taskDescription = description;
	}
}
```

**TaskRepository.java**
```java
package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Repository für die Task-Entität.
 */
@Hidden
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Findet eine Aufgabe anhand ihrer Beschreibung.
     * @param taskDescription
     * @return Task
     */
    Task findByTaskDescription(String taskDescription);
}
```

#### Hinweise zur Implementierung
Um die Version der Endpunkt im Controller nicht widerholt zu schreiben, haben wir versucht die Annotation `@RequestMapping` mit einem vordefinierten Endpoint zu versehen. Dies wollten wir diese in den Controller als `@RequestMapping("/v1")` und `@RequestMapping("/v2")` setzen. Leider hat dies nicht funktioniert, da Spring Boot, diese für das Mapping ignorierte und somit Duplikate erstellt worden sind. Somit mussten wir die Version in jeder Methode des Controllers angeben.

### Frontend
1. In `./frontend/src/domain/axios.ts` die `baseURL` mit der entsprechenden Version anpassen.

```javascript
  import axios from "axios";

  export const base = axios.create({
    baseURL: "http://localhost:8080/v1",
    headers: {
      "Content-Type": "application/json"
    },
  });
```

2. Bei neuen Versionen die URL in der `baseURL` entsprechend anpassen. Beispiel: `http://localhost:8080/v2`


### Swagger-UI
Die Swagger-UI ist ein nützliches Tool, um die API-Dokumentation zu erstellen und zu verwalten. Es zeigt die verschiedenen Endpunkte der API an und ermöglicht es, die verschiedenen Versionen der API zu trennen. Das Swagger-UI konnte durch das Hinzufügen des Dependencies `springdoc-openapi-starter-webmvc-ui` in der `pom.xml` hinzugefügt werden. Die verschiedenen Versionen der API werden in der Swagger-UI angezeigt und können getestet werden. Die Swagger-UI ist unter [Swagger-UI](http://localhost:8080/swagger-ui.html) erreichbar. Dieses Tool ist sehr einfach zum implementieren, da es nur ein Dependency benötigt und keine zusätzliche Konfiguration erforderlich ist.

## Zusammenfassung und Schlussfolgerung
Die API-Versionierung ist ein wichtiger Aspekt bei der Entwicklung von APIs. Es gibt verschiedene Methoden, um die Versionierung zu implementieren, jede mit ihren eigenen Vor- und Nachteilen. Die Wahl der richtigen Methode hängt von den Anforderungen des Projekts ab. In den meisten Fällen ist die URL-Parameter Methode die beste Wahl, da sie einfach zu implementieren, zuverlässig und leicht verständlich für Nutzer ist.
