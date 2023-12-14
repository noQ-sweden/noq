import Image from "next/image";
import Layout from "@/layouts/Layout";

export default function Home() {
  return (
    <Layout>
      <main className="p-6">
        <h1 className="text-3xl font-bold text-gray-900 mb-6">
          Välkommen Härberge
        </h1>
        <h2 className="text-2xl font-semibold text-gray-700 mb-4">
          Hantera förfrågningar
        </h2>
        <p className="">
          I vänster meny kan du välja att godkänna eller neka förfrågningar
          under fliken FÖRFRÅGNINGAR.
        </p>
      </main>
    </Layout>
  );
}
