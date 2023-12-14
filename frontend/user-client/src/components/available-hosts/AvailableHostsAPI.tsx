"use server"

import {AvailableHostsDTO} from "@/components/available-hosts/AvailableHostsDTO";

const BACKEND_URL_ENDPOINT = process.env.BACKEND_URL_ENDPOINT

export const fetchPage = async (token: string): Promise<AvailableHostsDTO> => {
  return fetch(`${BACKEND_URL_ENDPOINT}/api/user/available-hosts`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["available-hosts"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};
