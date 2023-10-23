import React from "react";
import {LinkToBookingsView, LinkToRequestsView} from "../routes/MainRoutes";
import {useLocation, useNavigate} from "react-router-dom";

const SideMenuLargeScreen = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const isLocationPath = (url: string) => location.pathname.includes(url);

  return (
      <div
          className="fixed inset-y-15 bg-base-200 left-0 h-full w-64 xxs:hidden md:block">
        <section className={""}>
          <ul className="menu bg-base-200">
            <li>
              <a className={`${isLocationPath(LinkToBookingsView()) ? "bg-zinc-300" : "bg-transparent"} `}
                 onClick={() => navigate(LinkToBookingsView())}>
                Bokningar
              </a>
            </li>
            <li>
              <a className={`${isLocationPath(LinkToRequestsView()) ? "bg-zinc-200" : "bg-transparent"} `}
                 onClick={() => navigate(LinkToRequestsView())}>
                Förfrågningar
              </a>
            </li>
          </ul>
        </section>
      </div>
  );
};

export default SideMenuLargeScreen;
