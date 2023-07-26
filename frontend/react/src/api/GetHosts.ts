import api from "./ApiRootUrl";
import { IHost } from "../interfaces/IHost";

export const getAllHostsWithBeds = async () => {
  try {
    const response = await api.get<IHost[]>("api/host/get-all-available");

    return response;
  } catch (error) {
    console.error(error);
  }
};
