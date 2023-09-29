import { Outlet } from "react-router-dom";
import { ClientPageContext } from "../../../context/ClientPageContext"; 
import { SideBarComponenet } from "./SideBarComponent";
import { useContext} from "react";
export default function LandingPageComponent() {
 const clientPage = useContext(ClientPageContext) 


  return (
    <>
      <div className="mt-12 flex">
        <div className="flex-none">
          <aside>
            <SideBarComponenet name={clientPage.name}></SideBarComponenet>
          </aside>
        </div>
        <div className="grow">
          <Outlet />
        </div>
      </div>
    </>
  );
}
