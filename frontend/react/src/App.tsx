import {useState, useEffect, createContext} from "react";
import noqLogo from "/noQ.png";
import axios from "axios";
import MyReservations from "./pages/myReservations";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import VacantBedPage from "./pages/VacantBedPage";
import Login from "./views/Login";

interface IUserContext{
    userId: string
}

export const UserContext = createContext<IUserContext>({userId: ""})

function App() {
    const [userId, setUserId] = useState("1")



    return (
        <>
            <BrowserRouter>
                <UserContext.Provider value={{userId}}>
                    <Routes>
                        <Route path="/" element={<Login/>}/>
                        <Route path="/vacancies/:userId" element={<VacantBedPage/>}/>
                        <Route path="/reservation/:userId" element={<MyReservations/>}/>
                    </Routes>
                </UserContext.Provider>

            </BrowserRouter>

        </>
    );
}

export default App;
