"use server"

import {AvailableHostsDTO} from "@/components/available-hosts/AvailableHostsDTO";

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

export const fetchPage = async (token: string): Promise<AvailableHostsDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/user/available-hosts`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["bookings"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};
