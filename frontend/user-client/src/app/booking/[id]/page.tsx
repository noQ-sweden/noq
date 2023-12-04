import {Metadata} from "next";
import {BookingPageDTO, bookingPageMock} from "@/components/booking/BookingPageDTO";
import BookingPage from "@/components/booking/BookingPage";

export const metadata: Metadata = {
  title: 'Noq',
}
const CLIENT_DOMAIN = process.env.CLIENT_DOMAIN

const getData = async (token: string, id: string): Promise<BookingPageDTO> => {
  return fetch(`${CLIENT_DOMAIN}/api/user/booking/${id}`, {
    method: "GET",
    cache: "no-store",
    next: {tags: ["booking"]},
    headers: {Authorization: "Bearer " + token},
  }).then(res => {
    if (res.ok) return res.json();
    return Promise.reject(res)
  }).catch(reason => {
    console.error(reason)
  });
};

export default async function Page({params}: { params: { id: string } }) {
  const token = "fakeToken";
  const data = await getData(token, params.id)

  return (
      <>
        <BookingPage data={data ? data : bookingPageMock}/>
      </>
  )
}
