import api from "./ApiInstance";

export const getReservation = async (userId: string | undefined) => {
  try {
    const response = await api.get(`/api/reservation/${userId}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};
