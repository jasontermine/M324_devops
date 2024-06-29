<template>
  <div style="width: 50%; margin: auto">
    <table>
      <thead>
        <tr>
          <th>Task</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
          <tr v-for="(task, index) in todos" :key="task.taskdescription">
            <td>{{ "#" + (index + 1) + ": " + task.taskdescription }}</td>
            <td>
              <button
                id="deleteButton"
                type="button"
                @click="() => handleDelete(task.taskdescription)"
              >
                Done
              </button>
            </td>
          </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { useTodoStore, type ITodo } from "@/stores/todoStore";
import { computed, type ComputedRef, onMounted } from "vue";

const todoStore = useTodoStore();
const todos: ComputedRef<ITodo[]> = computed(() => todoStore.getTodoList);

/**
 * Handles the delete button click event.
 * @param {string} taskdescription - The task description to delete
 */
const handleDelete = (taskdescription: string): void => {
  todoStore.deleteTodo(taskdescription);
};

/**
 * Fetches the todo list on component mount.
 */
onMounted(() => {
  todoStore.fetchTodoList()
})
</script>
