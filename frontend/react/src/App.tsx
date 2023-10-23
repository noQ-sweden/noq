import RoutingView from "./might_delete/RoutingView";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {NavbarDefault} from "./pages/routing/NavbarDefault";
import LandingComponent from "./pages/routing/LandingComponent";
import ClientPage from "./pages/client/ClientPage";
import VacanciesView from "./pages/client/views/vacanciesView/VacanciesView";
import ReservationsView from "./pages/client/views/reservationsView/ReservationsView";
import HostPage from "./pages/host/HostPage";
import SettingsView from "./pages/host/views/settingsView/SettingsView";
import RequestView from "./pages/host/views/requestsView/RequestView";


const App = () => (
    <BrowserRouter>
      <div className="bg-lilac">
        <NavbarDefault/>
        <div className="mx-auto max-w-screen-xl py-2 px-4 lg:px-8 lg:py-4 bg-white rounded-xl mt-5">
          <Routes>
            <Route path="/" element={<LandingComponent/>}/>
            <Route path="/client/:clientId" element={<ClientPage/>}>
              <Route index element={<VacanciesView/>}/>
              <Route path="/client/:clientId/vacancies" element={<VacanciesView/>}/>
              <Route path="/client/:clientId/reservations" element={<ReservationsView/>}/>
            </Route>
            <Route path="/host/:hostId" element={<HostPage/>}>
              <Route path="/host/:hostId/settings" element={<SettingsView/>}/>
              <Route path="/host/:hostId/requests" element={<RequestView/>}/>
            </Route>
          </Routes>
        </div>
      </div>
    </BrowserRouter>
);

export default App;
