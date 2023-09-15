import api from "./ApiRootUrl";


export const getAllHostRequests = async (hostId: string) => {
    try {
        const response = await api.get(
            `api/requests/get-reservations/${hostId}`
        );
        return response;
    } catch (error) {
        console.error(error);
    }
};


export const approveReservations = async (reservationData : string[], hostId: string) => {
    console.log(reservationData)
    try {
        const response = await api.put(`api/requests/approve-reservations/${hostId}`, reservationData);
        return response;
    } catch (error) {
        console.error(error);
    }
};

export const rejectReservations = async (reservationData : string[], hostId: string) => {
    console.log(reservationData)
    try {
        const response = await api.put(`api/requests/reject-reservations/${hostId}`, reservationData);
        return response;
    } catch (error) {
        console.error(error);
    }
};

