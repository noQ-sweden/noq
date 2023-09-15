import { BrowserRouter, Route, Routes } from "react-router-dom";
import LandingComponent from "./LandingComponent";
import { NavbarDefault } from "./NavbarDefault";
import VacanciesView from "../guest/views/vacanciesView/VacanciesView";
import ReservationsView from "../guest/views/reservationsView/ReservationsView";
import SettingsView from "../host/views/settingsView/SettingsView";
import GuestPage from "../guest/GuestPage";
import HostPage from "../host/HostPage";
import RequestsView from "../host/views/requestsView/RequestsView";

export default function RoutingView() {
  return (
    <>
      <BrowserRouter>
      <div className="bg-lilac">
        <NavbarDefault />
        <div className="mx-auto max-w-screen-xl py-2 px-4 lg:px-8 lg:py-4 bg-white rounded-xl mt-5">
          <Routes>
            <Route path="/" element={<LandingComponent />} />
            <Route path="/guest/:guestId" element={<GuestPage />}>
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
            <Route path="/host/:hostId" element={<HostPage />}>
              <Route
                path="/host/:hostId/settings"
                element={<SettingsView />}
                />
               <Route
                path="/host/:hostId/requests"
                element={<RequestsView />}
                />
            </Route>
          </Routes>
        </div>
        </div>
      </BrowserRouter>
    </>
  );
}
