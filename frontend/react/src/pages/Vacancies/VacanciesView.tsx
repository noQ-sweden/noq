import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { FaBed, FaCheck, FaRegCalendarAlt } from "react-icons/fa";
import { RxDotsHorizontal } from "react-icons/rx";
import { IHost } from "../../interfaces/IHost";
import HostCard from "../Host/components/HostCard";
import { useNavigate, useParams } from "react-router-dom";
import { getHosts } from "../../api/FetchData";
const VacantBedPage = () => {
  const [hosts, setHosts] = useState<IHost[]>([]);
  const navigate = useNavigate();

  useEffect(() => {
    getHosts(setHosts);
  }, []);

  const createReservation = async (
    hostId: string,
    userId: string | undefined
  ) => {
    try {
      const reservationData = { hostId, userId };
      await axios.post(
        "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/reservation/create",
        reservationData
      );
      // await axios.post("http://localhost:8080/api/reservation/create", reservationData)
      navigate(`/reservation/${userId}`);
    } catch (error) {
      console.error(error);
    }
  };

  const handleOnClick = (hostId: string, userId: string | undefined) => {
    createReservation(hostId, userId);
    console.log(hostId);
  };

  return (
    <div className="h-screen bg-white text-black">
      <div className="bg-blue-200 flex justify-center items-center h-20">
        <h2 className="text-2xl font-semibold">BOKA SÄNG</h2>
      </div>
      <div className="flex items-center justify-evenly pt-8">
        <span className="text-3xl text-green-500">
          <FaRegCalendarAlt />
        </span>
        <span className="text-3xl text-gray-500">
          <RxDotsHorizontal />
        </span>
        <span className="text-3xl text-yellow-400">
          <FaBed />
        </span>
        <span className="text-3xl text-gray-500">
          <RxDotsHorizontal />
        </span>
        <span className="text-3xl">
          <FaCheck />
        </span>
      </div>
      {hosts?.map((host) => (
        <HostCard key={host.hostId} host={host} handleOnClick={handleOnClick} />
      ))}
    </div>
  );
};

export default VacantBedPage;
