import axios from "axios";
import {useEffect, useState} from "react";
import {FaBed, FaCheck, FaRegCalendarAlt} from "react-icons/fa";
import {RxDotsHorizontal} from "react-icons/rx";
import {IHost} from "../interfaces/IHost";
import {Host} from "../mocks/Host";
import HostCard from "../components/HostCard";
import {useNavigate} from "react-router-dom";

const VacantBedPage = () => {
    const [hosts, setHosts] = useState<IHost[]>(Host);
    const navigate = useNavigate()

    // const getHosts = async () => {
    //   try {
    //     const response = await axios.get(
    //       "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/host/getall"
    //     );
    //     setHosts(response.data);
    //   } catch (error) {
    //     console.error(error);
    //   }
    // };

    // useEffect(() => {
    //   getHosts();
    // }, []);

    const handleOnClick = () => {
        // post createReseravtaion userId + bedId -> good enough hostId?

        navigate("/reservation")
    };


    return (
        <div className="h-screen bg-white text-black">
            <div className="bg-blue-200 flex justify-center items-center h-20">
                <h2 className="text-2xl font-semibold">BOKA SÄNG</h2>
            </div>
            <div className="flex items-center justify-evenly pt-8">
        <span className="text-3xl text-green-500">
          <FaRegCalendarAlt/>
        </span>
                <span className="text-3xl text-gray-500">
          <RxDotsHorizontal/>
        </span>
                <span className="text-3xl text-yellow-400">
          <FaBed/>
        </span>
                <span className="text-3xl text-gray-500">
          <RxDotsHorizontal/>
        </span>
                <span className="text-3xl">
          <FaCheck/>
        </span>
            </div>
            {hosts.map((host) => (
                <div
                    onClick={handleOnClick}
                >
                    <HostCard host={host}/>
                </div>
            ))}
        </div>
    );
};

export default VacantBedPage;
