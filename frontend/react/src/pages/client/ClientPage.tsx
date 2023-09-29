import {ClientPageProvider} from "../../context/ClientPageContext";
import LandingPageComponent from "./components/LandingPageComponent";


export default function ClientPage() {


    return (
        <ClientPageProvider>
            <LandingPageComponent></LandingPageComponent>
        </ClientPageProvider>
    );
}
