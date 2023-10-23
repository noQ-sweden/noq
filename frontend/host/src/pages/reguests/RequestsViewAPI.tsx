import {RequestsViewModel, UpdateReservationStatusField} from "./RequestsViewModel";

const port = import.meta.env.VITE_BACKEND_URL_ENDPOINT
export const FETCH_REQUESTS = "FETCH_REQUESTS"
const requestMapping = "api/host/requests"

export const fetchRequests = async (): Promise<RequestsViewModel> => {
  return fetch(`${port}/${requestMapping}`, {
    method: "GET"
  }).then(res => {
    if (res.ok) return res.json()
    Promise.reject(res)
  })
}

export const fetchUpdateReservationStatusField = async (reqBody: UpdateReservationStatusField): Promise<RequestsViewModel> => {
  console.log(reqBody)
  return fetch(`${port}/${requestMapping}/update-reservation-status-field`, {
    method: "PUT",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(reqBody)
  }).then(res => {
    if (res.ok) return res.json()
    Promise.reject(res)
  })
}

