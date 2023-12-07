import {Metadata} from "next";
import Requests from "@/components/requests/Requests";
import Layout from "@/layouts/Layout";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page() {
  return (
      <Layout>
        <Requests/>
      </Layout>
  )
}
