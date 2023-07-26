import { IHost } from "../../../interfaces/IHost";
import { useContext } from "react";
import { UserContext } from "../../../App";
import {Typography} from "@material-tailwind/react";

interface IHostCard {
  host: IHost;
  handleOnClick: (hostId: string, userId: string | undefined) => void;
}

export default function HostCard({ host, handleOnClick }: IHostCard) {
  const { userId } = useContext(UserContext);

  return (
    <>
      <Typography>{host.hostId}</Typography>
      <div className="flex items-center justify-center py-8">
        <div className="flex items-center border rounded-md shadow-xl p-5">
          <div
            className="flex flex-col"
            onClick={() => handleOnClick(host.hostId, userId)}
          >
            <h4 className="text-xl font-semibold mb-2">{host.name}</h4>
            <span>{`${host.address.street} ${host.address.streetNum},`}</span>
            <span>{`${host.address.postalCode} ${host.address.cityName}`}</span>
            <span className="mt-2">20kr</span>
          </div>
          <div className="w-24 h-24 bg-gray-300 rounded-full ml-6"></div>
        </div>
      </div>
    </>
  );
}
