import Image from "next/image";
import Layout from "@/layouts/Layout";

export default function Home() {
  return (
    <Layout>
      <main className="p-6">
        <h1 className="text-3xl font-bold text-gray-900 mb-6">
          Välkommen Brukare
        </h1>
        <h2 className="text-2xl font-semibold text-gray-700 mb-4">
          Hitta en sängplats för natten
        </h2>
        <p className="">
          I vänster meny kan du välja att hitta ledig sängplats under rubriken
          BOKA BOENDE.
        </p>
        <p className="mt-2">
          När du har skickat din förfrågan kan du hitta den under rubriken MINA BOKNINGAR.
        </p>
      </main>
    </Layout>
  );
}
