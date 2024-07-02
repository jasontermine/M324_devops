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

Diese Testklasse überprüft die Funktionalität der Methoden `addTask` und `deleteTask` in der `DemoApplication`-Klasse.

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

import com.example.demo.Model.Task;
import com.example.demo.Repository.TaskRepository;

/**
 * Diese Klasse enthält Tests für die Methoden der DemoApplication-Klasse.
 */
@SpringBootTest
class DemoApplicationTests {

    /**
     * Mock des TaskRepository-Objekts, das von der DemoApplication-Klasse verwendet wird.
     */
    @MockBean
    private TaskRepository taskRepository;

    /**
     * Das zu testende DemoApplication-Objekt.
     */
    @InjectMocks
    private DemoApplication demoApplication;

    /**
     * Diese Methode wird vor jedem Test aufgerufen und initialisiert die Mock-Objekte.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Fügen Sie einen Task zur lokalen Liste hinzu, um die Bedingung zu erfüllen
        Task task = new Task();
        task.setTaskDescription("Test Task");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskRepository.findAll()).thenReturn(tasks);
    }

    /**
     * Testet die getTasks-Methode der DemoApplication-Klasse.
     */
    @Test
    void testAddTask() {
      // Arrange
      String taskDescription = "{\"taskDescription\":\"Test Task\"}";

      Task savedTask = new Task();
      savedTask.setTaskDescription("Test Task");

      when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

      // Act
      String result = demoApplication.addTask(taskDescription);

      // Assert
      assertEquals("redirect:/", result); // Überprüft, ob die Methode die erwartete Weiterleitung zurückgibt
      verify(taskRepository).save(any(Task.class)); // Stellt sicher, dass die save-Methode des Repositories aufgerufen wird
      assertNotNull(savedTask.getTaskId()); // Überprüft, ob die ID des gespeicherten Tasks nicht null ist
      assertEquals("Test Task", savedTask.getTaskDescription()); // Überprüft, ob die Beschreibung des gespeicherten Tasks korrekt ist
    }

    /**
     * Testet die getTasks-Methode der DemoApplication-Klasse.
     */
    @Test
    void testDeleteTask() {
        // Arrange
        String taskDescription = "{\"taskDescription\":\"Test Task\"}";
        Task task = new Task();
        task.setTaskDescription("Test Task");

        when(taskRepository.findByTaskDescription("Test Task")).thenReturn(task);

        // Act
        String result = demoApplication.delTask(taskDescription);

        // Assert
        assertEquals("redirect:/", result);
        verify(taskRepository).findByTaskDescription("Test Task");
        verify(taskRepository).delete(task);
    }
}

```

### Erklärung der Tests

1. **Setup-Methode**:
   - Die `setUp` Methode wird vor jedem Test ausgeführt und initialisiert die Mocks und die lokale Task-Liste. Ein Task mit dem Namen "Test Task" wird zur lokalen Liste hinzugefügt, um die Bedingung für den Test zu erfüllen. Die `taskRepository.findAll` Methode wird durch `when(taskRepository.findAll()).thenReturn(tasks)` ersetzt, um die Liste zurückzugeben. Dies stellt sicher, dass die `getAllTasks` Methode in der `DemoApplication` Klasse die Liste korrekt zurückgibt.

2. **Test für `addTask`**:
   - Überprüft die Funktionalität der `addTask` Methode, indem sichergestellt wird, dass sie einen Task mit dem Namen "Test Task" erfolgreich hinzufügt und dabei die `taskRepository.save` Methode aufruft. Der Test gilt als bestanden, wenn der Task korrekt eingefügt und `taskRepository.save` ausgeführt wird. Eine Verbindung zur Datenbank ist für diesen Test nicht notwendig, da die `taskRepository.save` Methode durch ein Mock ersetzt wird. Mit `verify(taskRepository).save(any(Task.class))` wird überprüft, ob die Methode aufgerufen wurde. `assertNotNull(savedTask.getId())` überprüft, ob die ID des gespeicherten Tasks nicht null ist, und `assertEquals("Test Task", savedTask.getTaskDescription())` überprüft, ob die Beschreibung des Tasks korrekt ist.

3. **Test für `deleteTask`**:
   - Überprüft die Funktionalität der `deleteTask` Methode, indem sichergestellt wird, dass sie einen Task mit dem Namen "Test Task" erfolgreich löscht und dabei die `taskRepository.delete` Methode aufruft. Der Test gilt als bestanden, wenn der Task korrekt gelöscht und `taskRepository.delete` ausgeführt wird. Eine Verbindung zur Datenbank ist für diesen Test nicht notwendig, da die `taskRepository.delete` Methode durch ein Mock ersetzt wird. Mit `verify(taskRepository).delete(task)` wird überprüft, ob die Methode aufgerufen wurde.

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
Für die erfolgreiche Ausführung der Tests musste die `DemoApplication` Klasse angepasst werden. Die Methoden `addTask` und `deleteTask` wurden so implementiert, dass sie die Datenbankoperationen korrekt ausführen. Ebenso wurde die `getAllTasks` Methode angepasst, um die Aufgaben aus der Datenbank zu laden. Die Rückageben der Methoden wurde übernommen um weiter Anpassungen im Frontend zu vermeiden. Erweitert wurden sie jedoch mit einer Rückgabe eines Error-Strings, falls die Methode fehlschlägt. Die Klasse ist im Ordner `src/main/java/com/example/demo` zu finden.

```java
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
