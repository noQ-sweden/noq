"use server"
import {RequestsViewModel, UpdateReservationStatusField} from "./RequestsViewModel";

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const requestMapping = "api/host/requests"

export const fetchUpdateReservationStatusField = async (reqBody: UpdateReservationStatusField): Promise<RequestsViewModel> => {
  return fetch(`${CLIENT_DOMAIN}/${requestMapping}`, {
    method: "PUT",
    cache:  "no-cache",
    headers: {"Content-Type": "application/json", Authorization: "Bearer " + "token"},
    body: JSON.stringify(reqBody)
  }).then(value => value.json());
}
