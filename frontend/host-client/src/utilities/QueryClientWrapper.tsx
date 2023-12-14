"use client"

import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClientWrapper = new QueryClient({defaultOptions: {queries: {refetchOnWindowFocus: false}}})

export default function QueryClientWrapper({children}: { children: React.ReactNode }) {

  return (
      <QueryClientProvider client={queryClientWrapper}>
        {children}
      </QueryClientProvider>
  );
}
