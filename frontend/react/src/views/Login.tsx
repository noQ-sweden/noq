import "./Login.css";
import {useNavigate, useParams} from "react-router-dom";
import {useContext, useState} from "react";
import axios from "axios";
import {UserContext} from "../App";


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
                // const response = await axios.get("https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/user/${userId}");
                const response = await axios.get(`http://localhost:8080/api/user/${userId}`);
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
                <div className="page text-white">
                    <h1 className="text-2xl">Logga in</h1>
                    <div className="input-container">
                        <label className="mail-label" htmlFor="mail">E-post</label>
                        <input type="email" name="mail" id="mail"/>
                    </div>

                    <div className="input-container">
                        <label htmlFor="password">Lösenord</label>
                        <input type="password" name="password" id="password"/>
                    </div>
                    <button className="bg-gray-400 my-4" type="button" onClick={handleClick}>Logga in</button>
                </div>
            </>
        );
    }
;

export default Login;
