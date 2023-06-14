import api from "./ApiRootUrl";
import { IHost } from "../interfaces/IHost";

export const getHosts = async () => {
  try {
    const response = await api.get<IHost[]>("api/host/get-all");
    return response;
  } catch (error) {
    console.error(error);
  }
};
