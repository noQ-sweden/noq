import api from "./ApiRootUrl";

export const putNrBeds = async (numberOfBeds: number, hostId: string) => {

    try {
        const response = await api.put(`api/settings/create-beds/${hostId}`, null, {
            params: {
                numberOfBeds: numberOfBeds,
            },
        });
        return response.data;
    } catch (error) {
        console.error(error);
    }
};

export const updateName = async (id : string, name: string) => {
    const updateName = {id, name}
    try {
        const response = await api.put(`api/settings/update-name/`, updateName, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
        return response.data;
    } catch (error) {
        throw error;
    }
};