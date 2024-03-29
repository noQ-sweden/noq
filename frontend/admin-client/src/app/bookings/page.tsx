import {Metadata} from "next";
import Bookings from "@/components/bookings/Bookings";
import {BookingsPageDTO, bookingsPageDTOMock} from "@/components/bookings/BookingsPageDTO";

export const metadata: Metadata = {
  title: 'Noq',
}
const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string): Promise<BookingsPageDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/host/bookings`, {
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
        <Bookings data={data ? data : bookingsPageDTOMock}/>
      </>
  )
}
