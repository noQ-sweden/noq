import {Metadata} from "next";
import Requests from "@/components/requests/Requests";
import Bookings from "@/components/bookings/Bookings";

export const metadata: Metadata = {
  title: 'noq',
}

export default function Page() {
  return (
      <>
        <Bookings/>
      </>
  )
}
