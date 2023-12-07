import type {Metadata} from 'next'
import '../styles/globals.css'
import React, {ReactNode} from "react";
import Providers from "@/app/providers";

export const metadata: Metadata = {
  title: 'Noq',
  description: 'noq',
}

export default function RootLayout({children}: { children: ReactNode }) {
  return (
      <html lang="en">
      <body className={"bg-zinc-200 text-black"}>
      <Providers>
        {children}
      </Providers>
      </body>
      </html>
  )
}
