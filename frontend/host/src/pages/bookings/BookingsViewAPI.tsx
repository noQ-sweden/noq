import {BookingsViewModel} from "./BookingsViewModel";

const port = import.meta.env.VITE_BACKEND_URL_ENDPOINT
export const FETCH_BOOKINGS = "FETCH_BOOKINGS"
const requestMapping = "api/bookings"

export const fetchBookings = async (): Promise<BookingsViewModel> => {
  return fetch(`${port}/${requestMapping}/bookings`, {
    method: "GET"
  }).then(res => {
    if (res.ok) return res.json()
    Promise.reject(res)
  })
}
