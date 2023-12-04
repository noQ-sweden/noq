import {Metadata} from "next";
import {MyBookingsPageDTO, requestsPageDTOMock} from "@/components/my-bookings/MyBookingsPageDTO";
import MyBookingsPage from "@/components/my-bookings/MyBookingsPage";

export const metadata: Metadata = {
  title: 'Noq',
}

const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string): Promise<MyBookingsPageDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/user/my-bookings`, {
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
        <MyBookingsPage data={data ? data : requestsPageDTOMock}/>
      </>
  )
}
