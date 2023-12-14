"use server"

import {MyBookingsPageDTO, UpdateReservationStatusField} from "./MyBookingsPageDTO";

const BACKEND_URL_ENDPOINT = process.env.BACKEND_URL_ENDPOINT

const requestMapping = "api/user/my-bookings"

export const fetchPage = async (token: string): Promise<MyBookingsPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["my-bookings"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};

export const fetchUpdateReservationStatusField = async (reqBody: UpdateReservationStatusField): Promise<MyBookingsPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}`, {
    method: "PUT",
    cache:  "no-cache",
    headers: {"Content-Type": "application/json", Authorization: "Bearer " + "token"},
    body: JSON.stringify(reqBody)
  }).then(value => value.json());
}
