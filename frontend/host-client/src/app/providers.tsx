"use client";

import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import {ReactNode} from "react";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false
    }
  }
});

export default function Providers({children}: { children: ReactNode }) {
  return (
      <QueryClientProvider client={queryClient}>
        {children}
      </QueryClientProvider>
  );
}
