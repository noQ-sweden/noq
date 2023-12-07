"use server"

import {bookingsPageDTOMock, MyLocationsDTO} from "@/components/my-locations/MyLocationsDTO";

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

export const getPage = async (token: string): Promise<MyLocationsDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/host/my-locations`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["my-locations"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
    return bookingsPageDTOMock
  });
};
