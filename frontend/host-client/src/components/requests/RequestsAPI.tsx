"use server"
import {BookingReqBody, RequestsPageDTO, requestsPageDTOMock} from "./RequestsPageDTO";

const BACKEND_URL_ENDPOINT = process.env.BACKEND_URL_ENDPOINT

const requestMapping = "api/host/requests"

export const fetchPage = async (token: string, hostId: string): Promise<RequestsPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["requests"]},
  }).then(res => {
    console.log(res)
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return requestsPageDTOMock
  });
};

export const fetchApproveRequest = async (token: string, reqBody: BookingReqBody): Promise<RequestsPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}/approve`, {
    method: "POST",
    cache: "no-store",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(reqBody),
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return requestsPageDTOMock
  });
};

export const fetchDenyRequest = async (token: string, reqBody: BookingReqBody): Promise<RequestsPageDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/${requestMapping}/deny`, {
    method: "POST",
    cache: "no-store",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(reqBody),
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return requestsPageDTOMock
  });
};
