import { GuestPageContext, GuestPageProvider } from "../../context/GuestPageContext";
import LandingPageComponent from "./components/LandingPageComponent";


export default function GuestPage() {



  return (
    <GuestPageProvider>
<LandingPageComponent></LandingPageComponent>
    </GuestPageProvider>
  );
}
