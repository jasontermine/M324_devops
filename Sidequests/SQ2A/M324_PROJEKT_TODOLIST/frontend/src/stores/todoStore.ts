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
  status: 0,
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
