import axios, { AxiosInstance } from "axios";
///! FOR PRODUCTION ! Please rename ROOT_URL = process.env.REACT_APP_API_URL
const ROOT_URL = "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/";
const local = "http://localhost:8080/"

const api: AxiosInstance = axios.create({
  baseURL: ROOT_URL,
});

export default api;
