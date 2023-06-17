import api from "./ApiRootUrl";
import { IUser } from "../interfaces/IUser";

export const getClient = async (userId: string | undefined) => {
  try {
    const response = await api.get<IUser>(`api/user/${userId}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};
