import {Metadata} from "next";
import {RequestsPageDTO, requestsPageDTOMock} from "@/components/requests/RequestsPageDTO";
import Requests from "@/components/requests/Requests";

export const metadata: Metadata = {
  title: 'Noq',
}

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string): Promise<RequestsPageDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/host/requests`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["requests"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if(res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};

export default async function Page() {
  const token = "fakeToken";
  const data = await getData(token)

  return (
      <>
        <Requests data={data ? data : requestsPageDTOMock}/>
      </>
  )
}
