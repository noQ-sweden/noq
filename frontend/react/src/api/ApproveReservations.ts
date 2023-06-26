import api from "./ApiRootUrl";

export const approveReservations = async (reservationData : string[], hostId: string) => {
    console.log(reservationData)
    try {
        const response = await api.put(`api/reservation/approve-reservations/${hostId}`, reservationData);
        return response;
    } catch (error) {
        console.error(error);
    }
};