import api from "./ApiRootUrl";
import {IReservationsViewModel} from "../pages/guest/views/reservationsView/IReservationsViewModel";

export const getReservationsView= async () => {
    try {
        const response = await api.get<IReservationsViewModel>("api/reservations");
        return response;
    } catch (error) {
        console.error(error);
    }
};
export const getReservation = async (userId: string | undefined) => {
    try {
        const response = await api.get(`/api/reservation/${userId}`);
        return response;
    } catch (error) {
        console.error(error);
    }
};
