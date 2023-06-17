import api from "./ApiRootUrl";

export const getAllHostRequests = async (hostId: string) => {
  try {
    const response = await api.get(
      `api/reservation/get-reservations/${hostId}`
    );
    return response;
  } catch (error) {
    console.error(error);
  }
};
