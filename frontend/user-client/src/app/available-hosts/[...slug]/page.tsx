import {Metadata} from "next";
import AvailableHosts from "@/components/available-hosts/AvailableHosts";
import Layout from "@/layouts/Layout";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page() {
  return (
      <Layout>
        <AvailableHosts/>
      </Layout>
  )
}
