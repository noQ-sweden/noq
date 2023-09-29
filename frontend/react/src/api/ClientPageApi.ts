import { IClientPageModel } from "../pages/client/IClientPageModel";
import api from "./ApiRootUrl";

export const getClient = async (clientId: string | undefined) => {
  try {
    const response = await api.get<IClientPageModel>(`api/user-page/${clientId}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};
