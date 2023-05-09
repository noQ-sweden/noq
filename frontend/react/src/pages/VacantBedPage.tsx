import axios from "axios";
import { useEffect, useState } from "react";
import { FaBed, FaCheck, FaRegCalendarAlt } from "react-icons/fa";
import { RxDotsHorizontal } from "react-icons/rx";
import { IHost } from "../interfaces/IHost";

const VacantBedPage = () => {
  const [hosts, setHosts] = useState<IHost[]>([]);

  const getHosts = async () => {
    try {
      const response = await axios.get(
        "https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/host/getall"
      );
      setHosts(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getHosts();
  }, []);

  const handleOnClick = () => {
    console.log("Routing to next page on click works!");
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
      {hosts.map((host) => (
        <div
          onClick={handleOnClick}
          className="flex items-center justify-center py-8"
        >
          <div className="flex items-center border rounded-md shadow-xl p-5">
            <div className="flex flex-col">
              <h4 className="text-xl font-semibold">{host.name}</h4>
              <span className="text-blue-600 my-1">
                {`${host.address.street} ${host.address.streetNum},
                 ${host.address.postalCode} ${host.address.cityName}`}
              </span>
              <span>20kr</span>
            </div>
            <div className="w-24 h-24 bg-gray-300 rounded-full"></div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default VacantBedPage;
