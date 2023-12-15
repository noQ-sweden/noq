"use client";

import React, { useState } from "react";
import {
  AvailableHostsDTO,
  Host,
} from "@/components/available-hosts/AvailableHostsDTO";
import Button1 from "@/libs/Button1";
import Link from "next/link";

interface RequestItemProps {
  availableHost: Host;
  requestsViewModel: AvailableHostsDTO;
  setRequestsViewModel: (requestsViewModel: AvailableHostsDTO) => void;
}

const AvailableBookingItem = (props: RequestItemProps) => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const onClickHost = (id: string) => {
    console.log(id);
  };

  return (
    <>
      <div
        key={props.availableHost.hostId}
        className="max-w-md rounded overflow-hidden shadow-lg bg-white p-4 m-4"
      >
        <div className="grid grid-cols-2 p-2">
          <div className="place-self-center">
            <h3 className="text-lg font-semibold">
              {props.availableHost.name}
            </h3>
            <p className="text-gray-700 text-base">
              {props.availableHost.address1}
            </p>
            <p className="text-gray-700 text-base">
              {props.availableHost.address2}
            </p>
          </div>
          <div className="flex flex-col justify-between items-center">
            <p className="text-gray-600 text-l font-semibold">Lediga platser</p>
            <p className="text-l font-semibold">
              {props.availableHost.countOfAvailablePlaces}
            </p>
            <div className="text-right mt-4">
              <Link href={`/booking/${props.availableHost.hostId}`}>
                <Button1
                  title={"Välj boställe"}
                  isLoading={isLoading}
                  onClick={() => onClickHost(props.availableHost.hostId)}
                />
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AvailableBookingItem;
