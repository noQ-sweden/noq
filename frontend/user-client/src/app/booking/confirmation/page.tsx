import {Metadata} from "next";
import ConfirmationPage from "@/components/booking/confirmation/ConfirmationPage";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page({params}: { params: { id: string } }) {
  return (
      <>
        <ConfirmationPage/>
      </>
  )
}
