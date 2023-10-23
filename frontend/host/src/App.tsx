import MainRoutes, {LinkToBookingsView, LinkToRequestsView} from "./routes/MainRoutes";
import React from "react";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
})

const App = () => (
    <QueryClientProvider client={queryClient}>
      <div className="drawer">
        <input id="my-drawer" type="checkbox" className="drawer-toggle"/>
        <div className="drawer-content">
          <MainRoutes/>
        </div>
        <div className="drawer-side">
          <label htmlFor="my-drawer" aria-label="close sidebar" className="drawer-overlay"></label>
          <ul className="menu p-4 w-80 min-h-full bg-base-200 text-base-content">
            <li><a href={LinkToBookingsView()}>Bokningar</a></li>
            <li><a href={LinkToRequestsView()}>Förfrågningar</a></li>
          </ul>
        </div>
      </div>
    </QueryClientProvider>
);

export default App
