import api from "./ApiRootUrl";
import { IUser } from "../interfaces/IUser";

export const getGuest = async (userId: string | undefined) => {
  try {
    const response = await api.get<IUser>(`api/user-page/${userId}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};
