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
1. Weiteres Dependency ins `pom.xml` hinzufügen.

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

2. Alles dupliziert und die Endpoints mit /v1 oder /v2 ergänzt.
  
  ```java
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
  ```

3. In `Task.java` und `TaskRepository.java` die Annotation `@Hidden` um diese in der WEBview auszublenden

### Frontend
1. In `./frontend/src/domain/axios.ts` die `baseURL` entsprechend mit der entsprechenden Version anpassen.

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


## Zusammenfassung und Schlussfolgerung
Die API-Versionierung ist ein wichtiger Aspekt bei der Entwicklung von APIs. Es gibt verschiedene Methoden, um die Versionierung zu implementieren, jede mit ihren eigenen Vor- und Nachteilen. Die Wahl der richtigen Methode hängt von den Anforderungen des Projekts ab. In den meisten Fällen ist die URL-Parameter Methode die beste Wahl, da sie einfach zu implementieren, zuverlässig und leicht verständlich für Nutzer ist.
