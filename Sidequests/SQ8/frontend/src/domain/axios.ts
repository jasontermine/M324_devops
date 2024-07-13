import axios from "axios";

export const base = axios.create({
  baseURL: "http://localhost:8080/v1",
  headers: {
    "Content-Type": "application/json"
  },
});
