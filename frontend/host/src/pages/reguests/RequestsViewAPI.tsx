import {RequestsViewModel} from "./RequestsViewModel";

const port = import.meta.env.VITE_BACKEND_URL_ENDPOINT
export const FETCH_REQUESTS = "FETCH_REQUESTS"
const requestMapping = "api/host"

export const fetchRequests = async (): Promise<RequestsViewModel> => {
  return fetch(`${port}/${requestMapping}/requests`, {
    method: "GET"
  }).then(res => {
    if (res.ok) return res.json()
    Promise.reject(res)
  })
}
