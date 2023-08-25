import { Outlet } from "react-router-dom";
import { GuestPageContext } from "../../../context/GuestPageContext"; 
import { SideBarComponenet } from "./SideBarComponent";
import { useContext} from "react";
export default function LandingPageComponent() {
 const guestPage = useContext(GuestPageContext) 


  return (
    <>
      <div className="mt-12 flex">
        <div className="flex-none">
          <aside>
            <SideBarComponenet name={guestPage.name}></SideBarComponenet>
          </aside>
        </div>
        <div className="grow">
          <Outlet />
        </div>
      </div>
    </>
  );
}
