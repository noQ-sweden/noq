import { BrowserRouter, Route, Routes } from "react-router-dom";
import LandingComponent from "./LandingComponent";
import GuestView from "../guest/GuestPage";
import HostView from "../host/HostView";
import { NavbarDefault } from "./NavbarDefault";
import VacanciesView from "../guest/views/vacanciesView/VacanciesView";
import ReservationsView from "../guest/views/reservationsView/ReservationsView";

export default function RoutingView() {
  return (
    <>
      <BrowserRouter>
      <div className="bg-lilac">
        <NavbarDefault />
        <div className="mx-auto max-w-screen-xl py-2 px-4 lg:px-8 lg:py-4 bg-white">
          <Routes>
            <Route path="/" element={<LandingComponent />} />
            <Route path="/guest/:guestId" element={<GuestView />}>
              <Route index element={<VacanciesView />} />
              <Route
                path="/guest/:guestId/vacancies"
                element={<VacanciesView />}
              />
              <Route
                path="/guest/:guestId/reservations"
                element={<ReservationsView />}
              />
            </Route>
            <Route path="/host/:hostId" element={<HostView />}>
              <Route
                path="/host/:hostId/vacancies"
                element={<VacanciesView />}
              />
            </Route>
          </Routes>
        </div>
        </div>
      </BrowserRouter>
    </>
  );
}
