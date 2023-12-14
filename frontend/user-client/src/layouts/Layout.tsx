"use client";
import React, { ReactNode, useEffect, useState } from "react";
import Header from "@/layouts/Header";
import SideMenuLargeScreen from "@/layouts/SideMenuLargeScreen";
import { usePathname } from "next/navigation";

type Props = {
  children: ReactNode;
};

const Layout = ({ children }: Props) => {
  const pathname = usePathname();
  const [selected, setSelected] = useState<string>(pathname);

  useEffect(() => {
    setSelected(pathname);
  }, []);

  return (
    <div
      aria-label={"mobile_drawer_wrapper"}
      style={{ minHeight: "100vh" }}
      className={"flex flex-col bg-white"}
    >
      <div className="drawer">
        <input id="my-drawer" type="checkbox" className="drawer-toggle" />
        <div className="drawer-content">
          <Header setSelected={setSelected} />
          <SideMenuLargeScreen selected={selected} setSelected={setSelected} />
          <main className="flex-1 md:ml-72 md:mt-5">{children}</main>
        </div>
        <div className="drawer-side">
          <label
            htmlFor="my-drawer"
            aria-label="close sidebar"
            className="drawer-overlay"
          ></label>
          <ul className="menu p-4 w-80 min-h-full bg-zinc-50 text-base-content">
            <li className={"text-black"}>
              <a className={"p-5 hover:primary"} href={"available-hosts"}>
                Bokningar
              </a>
            </li>
            <li className={"text-black"}>
              <a className={"p-5 hover:primary"} href={"my-bookings"}>
                Förfrågningar
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Layout;
