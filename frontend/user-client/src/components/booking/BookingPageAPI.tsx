"use server"

import {BookingReqBody} from "@/components/booking/BookingPageDTO";

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

export const fetchSendHostRequest = async (reqBody :BookingReqBody) => {
  return fetch(`${CLIENT_DOMAIN}/api/user/booking`, {
    method: "PUT",
    headers: {Authorization: "Bearer " + "token", "Content-Type": "application/json"},
    body: JSON.stringify(reqBody)
  }).then(res => res.json())
};
