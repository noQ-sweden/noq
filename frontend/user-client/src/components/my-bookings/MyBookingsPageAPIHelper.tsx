"use server"

import {MyBookingsPageDTO, UpdateReservationStatusField} from "./MyBookingsPageDTO";

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const requestMapping = "api/host/requests"

export const fetchUpdateReservationStatusField = async (reqBody: UpdateReservationStatusField): Promise<MyBookingsPageDTO> => {
  return fetch(`${CLIENT_DOMAIN}/${requestMapping}`, {
    method: "PUT",
    cache:  "no-cache",
    headers: {"Content-Type": "application/json", Authorization: "Bearer " + "token"},
    body: JSON.stringify(reqBody)
  }).then(value => value.json());
}
