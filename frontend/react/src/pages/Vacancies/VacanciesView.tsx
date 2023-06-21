import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { IHost } from "../../interfaces/IHost";
import HostCard from "../Host/components/HostCard";
import { useNavigate, useParams } from "react-router-dom";
import { getHosts } from "../../api/GetHosts";
import { createReservation } from "../../api/CreateReservation";
import ClientViewHeader from "../../components/ClientViewHeader";



const VacantBedPage = () => {
  const [hosts, setHosts] = useState<IHost[]>([]);
  const navigate = useNavigate();

  const fetchHosts = async () => {
    try {
      const response = await getHosts();
      if (response?.data) {
        setHosts(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchHosts();
  }, []);

  const handleOnClick = async (hostId: string, userId: string | undefined) => {
    try {
      const reservationData = { hostId, userId };
      await createReservation(reservationData.hostId, userId);
      console.log(userId);
      navigate(`/reservation/${userId}`);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="h-screen bg-white text-black">
   <ClientViewHeader></ClientViewHeader>
      {hosts?.map((host) => (
        <HostCard key={host.hostId} host={host} handleOnClick={handleOnClick} />
      ))}
    </div>
  );
};

export default VacantBedPage;
