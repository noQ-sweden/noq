import api from "./ApiRootUrl";

export const putNrBeds = async (numberOfBeds : number, hostId: string) => {

    try {
        const response = await api.put(`api/bed/create-beds/${hostId}`, null, {
            params: {
                numberOfBeds: numberOfBeds,
            },
        });
        console.log(response)
        return response;

    } catch (error) {
        console.error(error);
    }
};