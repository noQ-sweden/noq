import api from "./ApiRootUrl";
import {IVacanciesViewModel} from "../pages/guest/views/vacanciesView/IVacanciesViewModel";

export const getVacanciesView = async () => {
    try {
        const response = await api.get<IVacanciesViewModel>("api/vacancies");
        return response;
    } catch (error) {
        console.error(error);
    }
};

export const createReservation = async (
    hostId: string,
    userId: string | undefined,
    bedId: string
) => {
    try {
        const reservationData = {hostId, userId, bedId};
        const response = await api.post("api/vacancies/create-reservation", reservationData);
        return response;
    } catch (error) {
        console.error(error);
    }
};


