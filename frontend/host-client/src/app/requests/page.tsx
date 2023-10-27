import {Metadata} from "next";
import Requests from "@/components/requests/Requests";

export const metadata: Metadata = {
  title: 'noq',
}

export default function Page() {
  return (
      <>
        <Requests/>
      </>
  )
}
