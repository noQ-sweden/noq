import {Metadata} from "next";
import {AvailableHostsDTO, availableHostsDTOMock} from "@/components/available-hosts/AvailableHostsDTO";
import AvailableHosts from "@/components/available-hosts/AvailableHosts";

export const metadata: Metadata = {
  title: 'Noq',
}
const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string): Promise<AvailableHostsDTO> => {
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

export default async function Page() {
  const token = "fakeToken";
  const data = await getData(token)

  return (
      <>
        <AvailableHosts data={data ? data : availableHostsDTOMock}/>
      </>
  )
}
