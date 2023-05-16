import {useState, useEffect} from "react";
import noqLogo from "/noQ.png";
import axios from "axios";
import MyReservations from "./pages/myReservations";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import VacantBedPage from "./pages/VacantBedPage";
import Login from "./views/Login";

function App() {
    const [message, setMessage] = useState("");

    const getMessage = async () => {
        try {
            const response = await axios.get(
                "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/reservation/hello"
            );
            setMessage(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/vacancies/:userId" element={<VacantBedPage/>}/>
                    <Route path="/reservation" element={<MyReservations/>}/>
                </Routes>
            </BrowserRouter>

        </>
    );
}

export default App;
