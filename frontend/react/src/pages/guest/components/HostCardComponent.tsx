import React from "react";
import {IAddress} from "../views/vacanciesView/IVacanciesViewModel";


interface IHostCardComponent{
  hostId: string,
  bedId: string
  hostName: string,
  address: IAddress,
  hostImg: string,
  onClick: (hostId: string, bedId: string) => {}
}

export default function HostCardComponent({hostId, bedId, hostName, address, hostImg, onClick} : IHostCardComponent) {
  return (
    <>
      {bedId}
      <div className="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl "
           onClick={() => onClick(hostId, bedId)}>
        <div className="md:flex">
          <div className="md:shrink-0">
            <img
              className="h-48 w-full object-cover md:h-63 md:w-64"
              src="/images/host4.jpg"
              alt="Modern building architecture"
            />
          </div>
          <div className="p-8">
            <div className="uppercase tracking-wide text-sm text-indigo-500 font-semibold">
            <h4 className="text-xl font-semibold mb-2">{hostName}</h4>
            </div>
            <p className="mt-2 text-slate-500 flex flex-col">
            <span>{address.street} {address.streetNum}</span>
            <span>{address.postalCode}, {address.cityName}</span>
            </p>
          </div>
        </div>
      </div>
    </>
  );
}
