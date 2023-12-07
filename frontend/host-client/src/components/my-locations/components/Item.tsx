"use client"

import React from "react";
import {Location, MyLocationsDTO} from "@/components/my-locations/MyLocationsDTO";
import Button2 from "@/libs/Button2";

interface RequestItemProps {
  location: Location
  myLocationsDTO: MyLocationsDTO
  setMyLocationsDTO: (res: MyLocationsDTO) => void
}

const Item = (props: RequestItemProps) => {

  return (
      <div key={props.location.id} className={"border-2 border-zinc-300 p-1 rounded"}>
        <p>{props.location.name}</p>
        <p>{props.location.address1}</p>
        <p>{props.location.address2}</p>
        <p>{`${props.location.countOfAvailablePlaces} / ${props.location.totalAvailablePlaces}`}</p>
        <div className={""}>
          <Button2 isLoading={false}
                   onClick={() => console.log("")}
                   title={"Ta bort"}
          />
        </div>
      </div>
  )
}

export default Item;
