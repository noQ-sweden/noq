import axios, { AxiosInstance } from "axios";

const ROOT_URL = "http://localhost:8080/";

const api: AxiosInstance = axios.create({
  baseURL: ROOT_URL,
});

export default api;
