import {Metadata} from "next";
import ConfirmationPage from "@/components/booking/confirmation/ConfirmationPage";
import Layout from "@/layouts/Layout";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page({params}: { params: { id: string } }) {
  return (
      <Layout>
        <ConfirmationPage/>
      </Layout>
  )
}
