import api from "./ApiRootUrl";
import {IVacanciesViewModel} from "../pages/client/views/vacanciesView/IVacanciesViewModel";

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
    clientId: string | undefined,
    bedId: string
) => {
    try {
        const reservationData = {hostId, clientId, bedId};
        const response = await api.post("api/vacancies/create-reservation", reservationData);
        return response;
    } catch (error) {
        console.error(error);
    }
};


