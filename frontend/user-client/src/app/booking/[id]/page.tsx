import {Metadata} from "next";
import BookingPage from "@/components/booking/BookingPage";
import Layout from "@/layouts/Layout";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page({params}: { params: { id: string } }) {
  return (
      <Layout>
        <BookingPage id={params.id}/>
      </Layout>
  )
}
