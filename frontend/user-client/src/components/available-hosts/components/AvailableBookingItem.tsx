"use client"

import React, {useState} from "react";
import {AvailableHostsDTO, Host} from "@/components/available-hosts/AvailableHostsDTO";
import Button1 from "@/libs/Button1";
import Link from "next/link";

interface RequestItemProps {
  availableHost: Host
  requestsViewModel: AvailableHostsDTO
  setRequestsViewModel: (requestsViewModel: AvailableHostsDTO) => void
}

const AvailableBookingItem = (props: RequestItemProps) => {
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const onClickHost = (id: string) => {
    // console.log(id);
  };

  return (
      <div key={props.availableHost.hostId} className={"border-2 border-zinc-300 p-1 rounded"}>
        <div className={"flex flex-col"}>
          <p>{props.availableHost.name}</p>
          <p>{props.availableHost.address1}</p>
          <p>{props.availableHost.address2}</p>
          <p>{props.availableHost.countOfAvailablePlaces} / {props.availableHost.totalAvailablePlaces}</p>
        </div>
        <div className={"flex gap-1"}>
          <Link href={`/booking/${props.availableHost.hostId}`}>
            <Button1 title={"Välj boställe"} isLoading={isLoading} onClick={() => onClickHost(props.availableHost.hostId)}/>
          </Link>
        </div>
      </div>
  )
}

export default AvailableBookingItem;
