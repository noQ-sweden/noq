import { SideBarComponenet } from "./components/SideBarComponent";
import { Outlet } from "react-router";

export default function GuestPage() {
  return (
    <>
      <div className="mt-12 flex">
        <div className="flex-none">
          <aside>
            <SideBarComponenet></SideBarComponenet>
          </aside>
        </div>
        <div className="grow">
          <Outlet />
        </div>
      </div>
    </>
  );
}
