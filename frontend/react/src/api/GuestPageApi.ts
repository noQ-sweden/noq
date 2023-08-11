import { IGuestPageModel } from "../pages/guest/IGuestPageModel";
import api from "./ApiRootUrl";

export const getGuest = async (userId: string | undefined) => {
  try {
    const response = await api.get<IGuestPageModel>(`api/user-page/${userId}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};
