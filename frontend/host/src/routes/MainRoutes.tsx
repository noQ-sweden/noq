import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from "../layouts/Header";
import LandingView from "../pages/landing/LandingView";
import RequestsView from "../pages/reguests/RequestsView";
import BookingsView from "../pages/bookings/BookingsView";
import React from "react";
import SideMenuLargeScreen from "../layouts/SideMenuLargeScreen";

export const LinkToLandingView = () => "/"
export const LinkToRequestsView = () => "/requests"
export const LinkToBookingsView = () => "/bookings"

const MainRoutes = () => {
  return (
      <BrowserRouter>
        <Header/>
        <SideMenuLargeScreen/>
        <div className={"md:ml-72 md:mt-5"}>
          <Routes>
            <Route path={LinkToLandingView()} element={<LandingView/>}/>
            <Route path={LinkToRequestsView()} element={<RequestsView/>}/>
            <Route path={LinkToBookingsView()} element={<BookingsView/>}/>
          </Routes>
        </div>
      </BrowserRouter>
  );
};

export default MainRoutes;
