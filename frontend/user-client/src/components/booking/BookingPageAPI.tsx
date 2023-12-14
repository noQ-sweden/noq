"use server"

import {BookingPageDTO, BookingReqBody} from "@/components/booking/BookingPageDTO";

const BACKEND_URL_ENDPOINT = process.env.BACKEND_URL_ENDPOINT

export const fetchPage = async (token: string, id: string): Promise<BookingPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/api/user/booking/${id}`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["booking"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};

export const fetchSendHostRequest = async (reqBody :BookingReqBody): Promise<string> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/api/user/booking/create-booking`, {
    method: "PUT",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(reqBody),
    next: {tags: ["create-booking"]},
    cache: "no-cache"
  }).then(res => {
    if (res.ok) return res.text();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return reason
  })
};
