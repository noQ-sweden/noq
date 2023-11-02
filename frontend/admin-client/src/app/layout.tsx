import type {Metadata} from 'next'
import {Inter} from 'next/font/google'
import '../styles/globals.css'
import Layout from "@/layouts/Layout";
import Providers from "@/app/providers";

const inter = Inter({subsets: ['latin']})

export const metadata: Metadata = {
  title: 'Noq',
  description: 'noq',
}

export default function RootLayout({children}: { children: React.ReactNode }) {
  return (
      <html lang="en">
      <body className={`${inter.className} bg-zinc-200 text-black`}>
      <Providers>
        <Layout>
          {children}
        </Layout>
      </Providers>
      </body>
      </html>
  )
}
