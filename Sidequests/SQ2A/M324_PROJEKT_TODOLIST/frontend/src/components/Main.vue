<template>
  <div style="margin: auto; width: 50%;">
    <div style="text-align: center;">
      <h1 id="title">ToDo Liste</h1>

      <form @submit.prevent="handleSubmit(todoDescription)">
        <input v-model="todoDescription"/>
        <button id="submitBtn" type="submit">Absenden</button>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, type Ref } from 'vue';
import { useTodoStore } from '@/stores/todoStore';

const todoStore = useTodoStore();
const todoDescription: Ref<string> = ref('');

/**
 * Handles the form submit event.
 * @param {string} input - The input from the form
 
 */
const handleSubmit = (input: string): void => {
  if (!input.trim()) return alert("Die Eingabe darf nicht leer sein");
  
  todoStore.postTodo(input);
};
</script>

<style scoped></style>
