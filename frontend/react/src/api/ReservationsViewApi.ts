import api from "./ApiRootUrl";
import { IReservationsViewModel } from "../pages/client/views/reservationsView/IReservationsViewModel";

export const getReservationsView= async () => {
    try {
        const response = await api.get<IReservationsViewModel>("api/reservations");
        return response;
    } catch (error) {
        console.error(error);
    }
};
export const getReservation = async (clientId: string | undefined) => {
    try {
        const response = await api.get(`/api/reservations/${clientId}`);
        return response;
    } catch (error) {
        console.error(error);
    }
};
