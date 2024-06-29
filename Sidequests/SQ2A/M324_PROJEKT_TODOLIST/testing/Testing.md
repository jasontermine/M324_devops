# Unit Testing Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Setup Instructions](#setup-instructions)
   - [Frontend Unit Tests](#frontend-setup)
   - [Java Unit Tests](#java-setup)
3. [Test Organization](#test-organization)
   - [Frontend Test Organization](#frontend-test-organization)
   - [Java Test Organization](#java-test-organization)
4. [Writing Tests](#writing-tests)
   - [Writing Frontend Unit Tests](#writing-frontend-tests)
   - [Writing Java Unit Tests](#writing-java-tests)
5. [Running Tests](#running-tests)
   - [Running Frontend Unit Tests](#running-frontend-tests)
   - [Running Java Unit Tests](#running-java-tests)
6. [Troubleshooting](#troubleshooting)
   - [Frontend Unit Test Issues](#frontend-troubleshooting)
   - [Java Unit Test Issues](#java-troubleshooting)

---

## Introduction

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

### Frontend Unit Tests

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
    
    Node.js von der [offiziellen Website](https://nodejs.org/en) herunterladen und installieren.

2. **Abhängigkeiten für das FE installieren**
    1. In das `frontend` Verzeichnis wechseln und anschliessend die Abhängigkeiten installieren.
    
        ```bash
        npm install
        ```

3. **Tests ausführen**
    1. Führen Sie die Tests mit dem folgenden Befehl aus:
    
        ```bash
        npm run test
        ```

## Test Aufbau

### Frontend Tests

- **Verzeichnis Aufbau:**
    ```
    src/
        components/
            Main.spec.ts (Test für Main.vue)
            TodoStore.spec.ts (Test für TodoStore.ts)
    ```

## Test Ausführung und Ergebnisse

### Main.vue Test

```ts
import { describe, it, expect, beforeEach } from 'vitest'
import { mount } from '@vue/test-utils'
import Main from '@/components/Main.vue'
import { createPinia, setActivePinia } from 'pinia'

describe('Main component tests', () => {
/**
 * Sets up the Pinia store because the TodoStore is used in the Main component
 */
  beforeEach(() => {
    setActivePinia(createPinia())
  })
  
  /**
   * Tests if the Main component renders / contains the correct text 
   */
  it('renders properly', () => {
    const wrapper = mount(Main)
    expect(wrapper.find("#title").text()).toStrictEqual("ToDo Liste")
    expect(wrapper.find("#submitBtn").text()).toStrictEqual('Absenden')
  });


  /**
   * Tests if the Main component does not contain the correct content 
   */  
  it("test fails if Element content does not match with actual text", () => {
    const wrapper = mount(Main)
    expect(wrapper.find("#title").text()).toStrictEqual("Todo Liste")
    expect(wrapper.find("#submitBtn").text()).toStrictEqual('ABsenden')
  });

})
```



### TodoStore.ts Test

```ts
// stores/counter.spec.ts
import { useTodoStore } from '@/stores/todoStore'
import { createPinia, setActivePinia } from 'pinia'
import { beforeEach, describe, expect, it } from 'vitest'
import { createApp } from 'vue'

/**
 * Sets up the Pinia store to allow Mocking of the TodoStore
 */
beforeEach(() => {
    setActivePinia(createPinia());
})

describe('TodoStore status', () => {

  it('TodoState status is 0 by default', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toBe(0)
  })

  it('TodoState status default value is smaller than test value', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toBeLessThan(1)
  })

  it('test fails if not equal to default status value', () => {
    const todoList = useTodoStore()

    expect(todoList.status).toEqual(1)
  })
})

describe('TodoStore data', () => {

    it('TodoState data is empty by default', () => {
        const todoList = useTodoStore()

        expect(todoList.data).toHaveLength(0)
    })
  
    it('test fails if default State Data is not empty', () => {
        const todoList = useTodoStore()

        expect(todoList.data).toHaveLength(1)
    })

  })
```


### Writing Java Unit Tests

- **Example Test (AppTest.java):**
    ```java
    import org.junit.Test;
    import static org.junit.Assert.*;

    public class AppTest {
        @Test
        public void testAppHasAGreeting() {
            App class
    ```

## Running Tests

### Running Frontend Unit Tests

- **Run Tests Using npm Scripts:**
    ```bash
    npm test
    ```

### Running Java Unit Tests

- **Run Tests Using Maven:**
    ```bash
    mvn test
    ```

## Troubleshooting

### Frontend Unit Test Issues

1. **Common Issues:**
    - Test fails unexpectedly.
    - Component not rendering as expected.

2. **Debugging Tips:**
    - Ensure all necessary dependencies are installed.
    - Check for typos or incorrect imports.
    - Use debugging tools like `console.log` to trace issues.

### Java Unit Test Issues

1. **Common Issues:**
    - Test fails with an exception.
    - Dependency injection issues.

2. **Debugging Tips:**
    - Check stack trace for specific errors.
    - Ensure all dependencies are correctly configured.
    - Use debugging tools in your IDE to step through the code.
