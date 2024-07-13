import { useTodoStore, type ITodoResponse } from "@/stores/todoStore";
import { createPinia, setActivePinia } from "pinia";
import { beforeEach, describe, expect, it } from "vitest";

beforeEach(() => {
  setActivePinia(createPinia());
});

// Mock / prepare the data being set
const mockData: ITodoResponse = {
  status: 201,
  data: [
    {
      taskDescription: "Task 1",
    },
    {
      taskDescription: "Task 4",
    },
  ],
};

describe("TodoStore status", () => {
  it("TodoState status is 0 by default", () => {
    const todoList = useTodoStore();

    expect(todoList.status).toBe(0);
  });

  it("TodoState status default value is smaller than test value", () => {
    const todoList = useTodoStore();

    expect(todoList.status).toBeLessThan(1);
  });
});

describe("TodoStore data", () => {
  it("TodoState data is empty by default", () => {
    const todoStore = useTodoStore();

    expect(todoStore.data).toHaveLength(0);
  });

  it("setTodo sets Todos and returns them correctly ", () => {
    const todoStore = useTodoStore();

    // Set the store data
    todoStore.setTodo(mockData);

    // Assert data
    expect(todoStore.status).toEqual(201);
    expect(todoStore.data[0].taskDescription).toStrictEqual("Task 1");
    expect(todoStore.data[1].taskDescription).toStrictEqual("Task 4");

    // Assert that Todos are not anything else
    expect(todoStore.status).not.toEqual(200);
    expect(todoStore.data[0].taskDescription).not.toStrictEqual("Task 2");
    expect(todoStore.data[1].taskDescription).not.toStrictEqual("Task 5");

    // Assert that Todo length is 2
    expect(todoStore.data).toHaveLength(2);

    // Assert that Todo length is not anything else
    expect(todoStore.data).not.toHaveLength(1);
    expect(todoStore.data).not.toHaveLength(3);
  });

  it("getTodoList returns the tasks correctly ", () => {
    const todoStore = useTodoStore();

    // Set the store data
    todoStore.setTodo(mockData);

    // Get the Data
    const todoList = todoStore.getTodoList;

    // Assert data
    expect(todoList[0].taskDescription).toStrictEqual("Task 1");
    expect(todoList[1].taskDescription).toStrictEqual("Task 4");

    // Assert that Todos are not anything else
    expect(todoList[0].taskDescription).not.toStrictEqual("Task 2");
    expect(todoList[1].taskDescription).not.toStrictEqual("Task 5");

    // Assert that Todo length is 2
    expect(todoList).toHaveLength(2);

    // Assert that Todo length is not anything else
    expect(todoList).not.toHaveLength(1);
    expect(todoList).not.toHaveLength(3);
  });
});
