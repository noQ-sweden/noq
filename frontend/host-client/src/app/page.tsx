import Image from 'next/image'
import Layout from "@/layouts/Layout";

export default function Home() {
  return (
      <Layout>
        <main className="flex min-h-screen flex-col items-center justify-between p-24">
          <div>Välkommen till NoQ</div>
        </main>
      </Layout>
  )
}
