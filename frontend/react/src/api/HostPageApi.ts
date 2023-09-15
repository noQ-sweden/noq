import api from "./ApiRootUrl";
import {IHostPageModel} from "../pages/host/IHostPageModel";

export const getHost = async (hostId: string | undefined) => {
    try {
        const response = await api.get<IHostPageModel>(`api/host-page/${hostId}`);
        return response;
    } catch (error) {
        console.error(error);
    }
};
