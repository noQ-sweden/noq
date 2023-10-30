import React, {ReactNode} from "react";
import Header from "@/layouts/Header";
import SideMenuLargeScreen from "@/layouts/SideMenuLargeScreen";

type Props = {
  children: ReactNode;
};

const Layout = ({children}: Props) => {
  return (
      <div aria-label={"mobile_drawer_wrapper"} style={{minHeight: '100vh'}} className={"flex flex-col"}>
        <div className="drawer">
          <input id="my-drawer" type="checkbox" className="drawer-toggle"/>
          <div className="drawer-content">
            <Header/>
            <SideMenuLargeScreen/>
            <main className="flex-1 md:ml-72 md:mt-5">{children}</main>
          </div>
          <div className="drawer-side">
            <label htmlFor="my-drawer" aria-label="close sidebar" className="drawer-overlay"></label>
            <ul className="menu p-4 w-80 min-h-full bg-zinc-50 text-base-content">
              <li className={"text-zinc-700"}><a href={"bookings"}>Bokningar</a></li>
              <li className={"text-zinc-700"}><a href={"requests"}>Förfrågningar</a></li>
            </ul>
          </div>
        </div>
      </div>
  );
};

export default Layout;
