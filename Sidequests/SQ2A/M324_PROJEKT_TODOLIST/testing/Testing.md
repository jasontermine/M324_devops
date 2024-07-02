# Unit Testing Documentation

## Inhaltsverzeichnis
1. [Einleitung](#einleitung)
2. [Setup Anweisungen](#setup-anweisungen)
    1. [Node.js und npm installieren](#nodejs-und-npm-installieren)
3. [Verzeichnis Aufbau für Tests](#verzeichnis-aufbau-für-tests)
    1. [Frontend](#frontend)
    2. [Backend](#backend)
4. [Tests Schreiben und Ergebnisse](#tests-schreiben-und-ergebnisse)
    1. [Frontend](#frontend-1)
        1. [Main.spec.ts Test](#mainspects-test)
            1. [Test Fehlgeschlagen](#test-fehlgeschlagen)
            2. [Angepasster Main.vue Code](#angepasster-mainvue-code)
        2. [TodoStore.spec.ts Test](#todostorespects-test)
            1. [Test Fehlgeschlagen](#test-fehlgeschlagen-1)
            2. [Angepasster TodoStore.ts Code](#angepasster-todostorets-code)
    2. [Backend](#backend-1)
        1. [DemoApplicationTests](#demoapplicationtests)
        2. [Erklärung der Tests](#erklärung-der-tests)
        3. [Konfiguration der In-Memory-Datenbank (H2)](#konfiguration-der-in-memory-datenbank-h2)
            1. [application.properties](#applicationproperties)
            2. [TestConfig.java](#testconfigjava)
        3. [Fehlgeschlagene und erfolgreiche Tests](#fehlgeschlagene-und-erfolgreiche-tests)
        4. [Angepasste Task Klasse](#angepasste-task-klasse)
        5. [Neue TaskRepository Klasse](#neue-taskrepository-klasse)
        6. [Angepasste DemoApplication Klasse](#angepasste-demoapplication-klasse)
5. [Tests Ausführen](#tests-ausführen)
    1. [Frontend](#frontend-2)
    2. [Backend](#backend-2)
---

## Einleitung

Diese Dokumentation beschreibt die Einrichtung, Organisation und Ausführung von Unit-Tests für Frontend- und Backend.

## Setup Anweisungen

Um die folgenden Schritte auszuführen, müssen Sie das Projekt von GitHub klonen:
    
```bash
git clone https://github.com/jasontermine/M324_devops.git
```

Anschliessend in das Verzeichnis `M324_devops` wechseln:
    
```bash
cd M324_devops
```

### Node.js und npm installieren

1. **Node.js und npm installieren**

    *Linux*: Terminal öffnen und folgenden Befehl ausführen:
    ```bash
    sudo apt install nodejs npm
    ```

    ```
    # installs nvm (Node Version Manager)
    curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash

    # download and install Node.js (you may need to restart the terminal)
    nvm install 20

    # verifies the right Node.js version is in the environment
    node -v # should print `v20.15.0`

    # verifies the right NPM version is in the environment
    npm -v # should print `10.7.0`
    ```
    *Windows* und *macOS*: 
    
    - Node.js von der [offiziellen Website](https://nodejs.org/en) herunterladen und installieren.

2. **Abhängigkeiten für das FE installieren**
    
    In das `frontend` Verzeichnis wechseln und anschliessend die Abhängigkeiten installieren.
    
    ```bash
    npm install
    ```

## Verzeichnis Aufbau für Tests

### Frontend

Die Main.vue Komponente wird getestet, um sicherzustellen, dass sie die richtigen Texte enthält. 
Der TodoStore wird ebenfalls getestet, um sicherzustellen, dass der Status standardmässig auf 0 gesetzt ist und die Todos korrekt gesetzt werden, wenn man die darin enthaltenen Funktionen aufruft.

- **Verzeichnis Aufbau:**
    ```
    src/
        components/
            __tests__/
                Main.spec.ts (Test für Main.vue)
                TodoStore.spec.ts (Test für TodoStore.ts)
    ```

### Backend

Im Backend-Verzeichnis `src/test/java/com/example/demo` finden Sie die Testklassen, die für die Unit-Tests verwendet werden.

## Tests Schreiben und Ergebnisse

### Frontend

### Main.spec.ts Test

 Test schlägt fehl, wenn der Test einen Text-Inhalt von "ToDo Liste" und "Absenden" erwartet, jedoch im Code nicht implementiert ist:
 
    
```ts
import { describe, it, expect, beforeEach } from 'vitest'
import { createPinia, setActivePinia } from 'pinia'
import { mount } from '@vue/test-utils'
import Main from '@/components/Main.vue'

describe('Main component tests', () => {
    /**
     * Sets up the Pinia store because the Main component uses the TodoStore
     */
    beforeEach(() => {
        setActivePinia(createPinia())
    })
    
    /**
    * Tests if the Main component contains the correct text 
    */
    it('renders properly', () => {
        const wrapper = mount(Main)
        expect(wrapper.find("#title").text()).toStrictEqual("ToDo Liste")
        expect(wrapper.find("#submitBtn").text()).toStrictEqual('Absenden')
    });

})
```
    
### Test Fehlgeschlagen

![Test Fehlgeschlagen](./bilder/Failed_frontend_test.png)

### Angepasster Main.vue Code

Test erfolgreich, da im Code die richtigen texte implementiert wurden:

```html
<template>
    <div style="margin: auto; width: 50%;">
        <div style="text-align: center;">
        <!-- Angepasst -->
        <h1 id="title">ToDo Liste</h1>

        <form @submit.prevent="handleSubmit(todoDescription)">
            <input v-model="todoDescription"/>
            <!-- Angepasst -->
            <button id="submitBtn" type="submit">Absenden</button>
        </form>
        </div>
    </div>
</template>

```

![Test Erfolgreich](./bilder/Passed_frontend_test.png)



### TodoStore.spec.ts Test


Test schlägt fehl, da der Test einen Status Standardwert von 0 erwartet, jedoch im Code '1' implementiert ist:

```ts
import { useTodoStore } from '@/stores/todoStore'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach, describe, expect, it } from 'vitest'

beforeEach(() => {
    setActivePinia(createPinia());
})

describe('TodoStore status', () => {

  it('TodoState status is 0 by default', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toBe(0)
  })

})
```

### Test Fehlgeschlagen

![Test Fehlgeschlagen](./bilder/Failed_TodoStore_frontend_test.png)

### Angepasster TodoStore.ts Code

Test erfolgreich, da im Code der Status auf 0 gesetzt wurde:

```ts
import { base } from "@/domain/axios";
import { defineStore } from "pinia";
import type { AxiosResponse } from "axios";

export interface ITodo {
  taskdescription: string;
}

interface ITodoResponse {
  status: number;
  data: Array<ITodo>;
}

const todoState: ITodoResponse = {
  status: 0, // Wert / Code wurde angepasst für Unit Test
  data: [],
};

type TodoState = typeof todoState;

export const useTodoStore = defineStore("todoStore", {
  state: (): TodoState => ({
    ...todoState,
  }),

  getters: {
    /**
     * Get the list of todos
     * @param {ITodo[]} state - The state of the store
     * @returns - The list of todos
     */
    getTodoList(state): Array<ITodo> {
      return state.data;
    },
  },

  actions: {
    /**
     * Set the todo list
     * @param {ITodoResponse} data - The response data from the API
     */
    setTodo(data: ITodoResponse): void {
      this.status = data.status;
      this.data = data.data;
    },

    /**
     * Fetch the todo list from the API and set the response data (Todos) to the store
     */
    fetchTodoList(): void {
      const response: Promise<AxiosResponse<ITodoResponse, any>> = base.get<ITodoResponse>("/");

      response
        .then((res: AxiosResponse<ITodoResponse, any>) => {
          const data: ITodoResponse = {
            status: res.status,
            // @ts-expect-error
            data: res.data,
          };

          this.setTodo(data);
        })
        .catch((err) => {
          console.error(err);
        });
    },

    /**
     * Post a new todo to the API and fetch the updated todo list
     * @param {string} text - The text of the todo
     */
    async postTodo(text: string): Promise<void> {
      const payload = {
        taskdescription: text.trim(),
      };

      await base
        .post<ITodoResponse>("/tasks", payload)
        .then((res: AxiosResponse<ITodoResponse, any>) => {
          this.fetchTodoList();
        })
        .catch((err) => {
          console.error(err);
        });
    },

    /**
     * Delete a todo from the API and fetch the updated todo list
     * @param {string} taskdescription - The task description of the todo
     */
    async deleteTodo(taskdescription: string): Promise<void> {
      const payload: ITodo = {
        "taskdescription": taskdescription,
      };

      await base
        .post(`/delete`, payload )
        .then((res: AxiosResponse<any, any>) => {
          this.fetchTodoList();
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
});
```

![Test Erfolgreich](./bilder/Passed_TodoStore_frontend_test.png)

### Backend

### DemoApplicationTests

Diese Testklasse überprüft die Funktionalität der Methoden `addTask` und `deleteTask` in der `DemoApplication`-Klasse. Um die Interaktionen mit einer Datenbank zu vermeiden, wird eine In-Memory-Datenbank verwendet. Das `TaskRepository` wird durch ein Mock-Objekt ersetzt, um die Datenbankoperationen zu simulieren. Die Tests überprüfen, ob die Methoden korrekt funktionieren und die erwarteten Ergebnisse zurückgeben.

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
```

### Erklärung der Tests

1. **Setup-Methode**:
   - Die `setUp` Methode wird vor jedem Test ausgeführt und initialisiert die Mock-Objekte. Ein neuer Task mit dem Namen "Test Task" wird erstellt und in einer Liste gespeichert. Die `taskRepository.findAll` Methode wird so konfiguriert, dass sie die Liste zurückgibt. Die `taskRepository.save` Methode wird so konfiguriert, dass sie den Task zurückgibt und eine Mock-ID setzt. Die `taskRepository.findByTaskDescription` Methode wird so konfiguriert, dass sie den Task anhand seiner Beschreibung zurückgibt.

2. **Test für `addTask` Methode**:
   - Der Test überprüft, ob die `addTask` Methode einen neuen Task hinzufügt und die erwarteten Ergebnisse zurückgibt. Ein neuer Task mit dem Namen "New Task" wird erstellt und die `taskRepository.save` Methode wird so konfiguriert, dass sie den Task zurückgibt. Die `taskRepository.findByTaskDescription` Methode wird so konfiguriert, dass sie `null` zurückgibt. Die Methode wird aufgerufen und die Ergebnisse werden überprüft.

3. **Test für `addTask` Methode (Konflikt)**:
    - Der Test überprüft, ob die `addTask` Methode einen Konflikt erkennt, wenn ein Task mit demselben Namen bereits existiert. Ein Task mit dem Namen "Test Task" wird erstellt und die `taskRepository.findByTaskDescription` Methode wird so konfiguriert, dass sie den Task zurückgibt. Die Methode wird aufgerufen und die Ergebnisse werden überprüft.

4. **Test für `deleteTask` Methode**:
    - Der Test überprüft, ob die `deleteTask` Methode einen Task löscht und die erwarteten Ergebnisse zurückgibt. Ein Task mit dem Namen "Test Task" wird erstellt und die `taskRepository.findByTaskDescription` Methode wird so konfiguriert, dass sie den Task zurückgibt. Die Methode wird aufgerufen und die Ergebnisse werden überprüft.

5. **Test für `deleteTask` Methode (nicht gefunden)**:
    - Der Test überprüft, ob die `deleteTask` Methode einen Fehler zurückgibt, wenn der Task nicht gefunden wird. Die `taskRepository.findByTaskDescription` Methode wird so konfiguriert, dass sie `null` zurückgibt. Die Methode wird aufgerufen und die Ergebnisse werden überprüft.

## Konfiguration der In-Memory-Datenbank (H2)
### application.properties
In der `application.properties` Datei wird die Konfiguration für die H2-Datenbank festgelegt. Die Datenbank wird im Arbeitsspeicher erstellt und die Tabellen werden beim Schließen der Verbindung gelöscht. Die `spring.jpa.hibernate.ddl-auto` Eigenschaft wird auf `create-drop` gesetzt, um die Tabellen beim Start der Anwendung zu erstellen und beim Beenden zu löschen.

```properties
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```	

### TestConfig.java
Die `TestConfig` Klasse wird verwendet, um eine H2-Datenbank im Arbeitsspeicher zu erstellen. Die Klasse wird mit der `@TestConfiguration` Annotation versehen, um sie als Konfiguration für die Tests zu kennzeichnen. Die `@Bean` Annotation wird verwendet, um eine `DataSource` zu erstellen und die Verbindungsinformationen für die H2-Datenbank festzulegen.
```java
package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Konfiguration für die Tests.
 */
@TestConfiguration
public class TestConfig {

    /**
     * Erstellt eine H2-Datenbank im Arbeitsspeicher.
     * @return DataSource
     */
    @Bean
    @Primary
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
```

## Fehlgeschlagene und erfolgreiche Tests

### Fehlgeschlagene Tests

ZU Beginn schlugen die Tests fehl, da die Methoden in der `DemoApplication` Klasse nicht korrekt aufgerufen wurden. Hier ist ein Screenshot der fehlgeschlagenen Tests:

![Fehlgeschlagene Tests](bilder/Failed_backend_test.png)

### Erfolgreiche Tests

Nachdem die Methoden in der `DemoApplication` Klasse angepasst wurden, um die Tests erfolgreich zu bestehen, sehen die Ergebnisse wie folgt aus:

![Erfolgreiche Tests](bilder/Working_backend_test.png)

## Angepasste Task Klasse

Um die Tests erfolgreich zu bestehen, musste eine permanente Speicherung der Tasks in der Datenbank implementiert werden. Hierzu wurde die komplette DemoApplication Klasse angepasst. Ausserdem wurde die Task Klasse so angepasst, dass diese mittels der Jakarta Persistence API (JPA) als Entity in der Datenbank gespeichert werden kann. Um eine sinnvolle Tabelle in der Datenbank zu erstellen, wurde die `@Table` Annotation verwendet, um den Namen der Tabelle festzulegen. Die `@Id` Annotation wurde verwendet, um das Attribut `id` als Primärschlüssel zu kennzeichnen. Die `@GeneratedValue` Annotation wurde verwendet, um den Primärschlüssel automatisch zu generieren.

Die Klasse ist im Ordner `src/main/java/com/example/demo/Model` zu finden.

```java
package com.example.demo.Model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representiert eine Aufgabe, die in der Todo-Liste angezeigt wird.
 
 */
@Entity
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

## Neue TaskRepository Klasse
Die TaskRepository Klasse wurde erstellt, um die Datenbankoperationen für die Task-Entität zu verwalten. Die Klasse erweitert das `JpaRepository` Interface, das von Spring Data JPA bereitgestellt wird. Die Klasse ist im Ordner `src/main/java/com/example/demo/Repository` zu finden.

```java
package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;

/**
 * Repository für die Task-Entität.
 */
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

## Angepasste DemoApplication Klasse
Für die erfolgreiche Ausführung der Tests musste die `DemoApplication` Klasse angepasst werden. Die Methoden `addTask` und `delTask` wurden so geändert, dass sie die Datenbankoperationen korrekt ausführen. Die Methoden verwenden das `TaskRepository`, um die Aufgaben hinzuzufügen und zu löschen. Die Methoden geben `ResponseEntity`-Objekte zurück, um den Statuscode und die Nachricht anzuzeigen.

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
    @GetMapping("/")
    public ResponseEntity<List<Task>> getTasks() {
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
    @PostMapping("/tasks")
    public ResponseEntity<String> addTask(@RequestBody String taskDescriptionJson) {
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
    @DeleteMapping("/tasks")
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

## Tests Ausführen

### Frontend

Um die Frontend-Tests auszuführen, verwenden Sie npm (mit der Vitest-Library). Gehen Sie ins Frontend-Verzeichnis und führen Sie die folgenden Befehle aus:

1. In das `frontend` Verzeichnis wechseln:
    ```bash
    cd frontend
    ```

2. Frontend Unit Tests ausführen:
    ```bash
    npm run test
    ```

3. (Optional) Einzelne Test-Files ausführen:

   In das '\_\_tests__' Verzeichnis navigieren:
    ```bash
    cd src/components/__tests__
    ```
    anschliessend:
   ```bash
    npm run test TodoStore.spec.ts
    ```
    oder
    ```bash
    npm run test Main.spec.ts
    ```
    
### Backend

Um die Backend-Tests auszuführen, verwenden Sie Maven. Gehen Sie ins Backend-Verzeichnis und führen Sie die folgenden Befehle aus:

1. Wechseln Sie in das Backend-Verzeichnis:
    ```bash
    cd backend
    ```

2. Führen Sie die Tests aus:
    ```bash
    mvn test
    ```
