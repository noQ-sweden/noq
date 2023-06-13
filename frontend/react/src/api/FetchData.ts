import axios, { AxiosResponse } from "axios";
import { IUser } from "../interfaces/IUser";
import { IHost } from "../interfaces/IHost";
export async function fetchData<T>(endpoint: string): Promise<T> {
  ///! FOR PRODUCTION ! Please rename ROOT_URL = process.env.REACT_APP_API_URL
  const ROOT_URL =
    "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/";
  const url = `${ROOT_URL}/${endpoint}`;
  console.log("Request URL:", url);

  try {
    const response: AxiosResponse<T> = await axios.get(url);
    return response.data;
  } catch (error) {
    throw new Error(`Error fetching data`);
  }
}

export const getClient = async (userId: string) => {
  try {
    const response = await fetchData<IUser>(`api/user/${userId}`);

    console.log(response);
    /*     response.reservation
      ? navigate(`/reservation/${response.id}`)
      : navigate(`/vacancies/${response.id}`); */
    return response;
  } catch (error) {
    console.error(error);
  }
};

export const getHosts = async (
  setHosts: React.Dispatch<React.SetStateAction<IHost[]>>
) => {
  try {
    const response = await fetchData<IHost[]>("api/host/get-all");
    setHosts(response);
    console.log(response);
  } catch (error) {
    console.error(error);
  }
};
