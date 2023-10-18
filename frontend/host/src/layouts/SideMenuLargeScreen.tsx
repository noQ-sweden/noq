import React from "react";
import {LinkToBookingsView, LinkToRequestsView} from "../routes/MainRoutes";
import {useNavigate} from "react-router-dom";

const SideMenuLargeScreen = () => {
  const navigate = useNavigate();
  return (
      <div
          className="fixed inset-y-15 bg-base-200 left-0 h-full w-64 xxs:hidden md:block">
        <section className={""}>
          <ul className="menu bg-base-200">
            <li><a onClick={() => navigate(LinkToBookingsView())}>Bokningar</a></li>
            <li><a onClick={() => navigate(LinkToRequestsView())}>Förfrågningar</a></li>
          </ul>
        </section>
      </div>
  );
};

export default SideMenuLargeScreen;
