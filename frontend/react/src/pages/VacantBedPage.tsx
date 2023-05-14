import axios from "axios";
import { useEffect, useState } from "react";
import { FaBed, FaCheck, FaRegCalendarAlt } from "react-icons/fa";
import { RxDotsHorizontal } from "react-icons/rx";
import { IHost } from "../interfaces/IHost";

const VacantBedPage = () => {
  // const [hosts, setHosts] = useState<IHost[]>([]);

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
    console.log("Routing to next page onClick works!");
  };

  const hosts: IHost[] = [
    {
      hostId: "c8116b2b-d509-41c5-ada6-4680f309293a",
      name: "Test-Härberget 1",
      address: {
        id: "70bc3278-9df5-47cd-a71b-70ae571582de",
        street: "Gatgatan",
        streetNum: "12",
        postalCode: "12345",
        cityName: "Stockholm",
      },
      image: "url/till/bild/pa/Harberget1.png",
      beds: 15,
    },
    {
      hostId: "d13d2b39-e23c-4e0e-bf81-db74a3b7c903",
      name: "Test-Härberget 2",
      address: {
        id: "57e5fc63-562c-4673-ab5e-846708a0f026",
        street: "Vägvägen",
        streetNum: "21",
        postalCode: "23546",
        cityName: "Lund",
      },
      image: "url/till/bild/pa/Harberget2.png",
      beds: 20,
    },
  ];

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
              <h4 className="text-xl font-semibold mb-2">{host.name}</h4>
              <span>{`${host.address.street} ${host.address.streetNum},`}</span>
              <span>{`${host.address.postalCode} ${host.address.cityName}`}</span>
              <span className="mt-2">20kr</span>
            </div>
            <div className="w-24 h-24 bg-gray-300 rounded-full ml-6"></div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default VacantBedPage;
