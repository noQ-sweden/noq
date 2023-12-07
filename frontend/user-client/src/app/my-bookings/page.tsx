import {Metadata} from "next";
import MyBookingsPage from "@/components/my-bookings/MyBookingsPage";
import Layout from "@/layouts/Layout";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page() {
  return (
      <Layout>
        <MyBookingsPage/>
      </Layout>
  )
}
