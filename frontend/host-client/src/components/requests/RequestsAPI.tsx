"use server"
import {RequestsPageDTO, requestsPageDTOMock} from "./RequestsPageDTO";

const BACKEND_URL_ENDPOINT = process.env.BACKEND_URL_ENDPOINT

const requestMapping = "api/host/requests"

export const fetchPage = async (token: string, hostId: string): Promise<RequestsPageDTO> => {
  console.log("fetchPage")
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["requests"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return requestsPageDTOMock
  });
};

// export const fetchUpdateReservationStatusField = async (reqBody: UpdateReservationStatusField): Promise<RequestsPageDTO> => {
//   return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}`, {
//     method: "PUT",
//     cache:  "no-cache",
//     headers: {"Content-Type": "application/json", Authorization: "Bearer " + "token"},
//     body: JSON.stringify(reqBody)
//   }).then(value => value.json());
// }
