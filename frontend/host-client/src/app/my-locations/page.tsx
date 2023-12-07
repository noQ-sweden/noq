import {Metadata} from "next";
import Layout from "@/layouts/Layout";
import MyLocations from "@/components/my-locations/MyLocations";

export const metadata: Metadata = {
  title: 'Noq',
}

export default async function Page() {
  return (
      <Layout>
        <MyLocations/>
      </Layout>
  )
}
