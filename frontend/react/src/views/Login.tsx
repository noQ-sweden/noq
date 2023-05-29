import "./Login.css";
import {useNavigate, useParams} from "react-router-dom";
import {useContext, useState} from "react";
import axios from "axios";
import {UserContext} from "../App";
import {BsFillFilePersonFill, TbPassword} from "react-icons/all";


interface IUser {
    id: string;
    name: string;
    reservation: boolean;
}

const Login = () => {
        const {userId} = useContext(UserContext)
        const navigate = useNavigate();

        const getUser = async () => {
            try {
                const response = await axios.get(`https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/user/${userId}`);
                //const response = await axios.get(`http://localhost:8080/api/user/${userId}`);
                console.log(response.data.reservation)
                response.data.reservation ? navigate(`/reservation/${response.data.id}`)
                    : navigate(`/vacancies/${response.data.id}`)
            } catch (error) {
                console.error(error);
            }
        }


        const handleClick = async () => {
            await getUser()
        }


        return (
            <>
                <div className="min-h-screen flex items-center justify-center">
                    <div className="bg-white rounded-lg shadow-lg p-8 w-80 max-w-sm min-width: 100%">
                        <h2 className="text-2xl font-bold mb-6">Välkommen</h2>
                        <div className="flex flex-col space-y-4">
                            <div className="flex items-center rounded-md border border-gray-300">
            <span className="rounded-l-md px-3 py-2">
            <BsFillFilePersonFill/>
            </span>
                                <input
                                    type="email"
                                    placeholder="E-post"
                                    className="flex-grow px-1 py-2 rounded-r-md focus:outline-none"
                                />
                            </div>
                            <div className="flex items-center rounded-md border border-gray-300">
            <span className=" rounded-l-md px-3 py-2">
                <TbPassword/>
            </span>
                                <input
                                    type="password"
                                    placeholder="Lösenord"
                                    className="px-1 py-2 rounded-r-md focus:outline-none"
                                />
                            </div>
                        </div>
                        <button className="bg-blue-500 text-white rounded-full px-1 mt-6" onClick={handleClick}>
                            Logga in
                        </button>
                    </div>
                </div>
            </>
        );
    }
;

export default Login;
