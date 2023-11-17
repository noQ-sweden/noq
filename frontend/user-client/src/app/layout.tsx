import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import Providers from "@/app/providers";
import Layout from "@/layouts/Layout";
import '../styles/globals.css'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
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