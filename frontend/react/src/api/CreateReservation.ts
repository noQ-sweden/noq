import api from "./ApiRootUrl";

export const createReservation = async (
  hostId: string,
  userId: string | undefined
) => {
  try {
    const reservationData = { hostId, userId };
    const response = await api.post("api/reservation/create", reservationData);
    return response;
  } catch (error) {
    console.error(error);
  }
};
