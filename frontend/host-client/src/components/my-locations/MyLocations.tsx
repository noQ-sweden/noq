"use client"
import React, {useEffect, useState} from 'react';
import MyAvailableHost from "@/components/my-locations/components/Item";
import {MyLocationsDTO} from "@/components/my-locations/MyLocationsDTO";
import {getPage} from "@/components/my-locations/MyLocationsAPI";

interface BookingsProps {
}

const MyLocations = (props: BookingsProps) => {
  const [myLocationsDTO, setMyLocationsDTO] = useState<MyLocationsDTO>();

  useEffect(() => {
    getPage("").then(setMyLocationsDTO)
  }, []);

  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12"}>
            <div className={"flex flex-col gap-1"}>
              {myLocationsDTO && myLocationsDTO.locations && myLocationsDTO.locations.map(location => {
                return (
                    <div key={location.id}>
                      <MyAvailableHost
                          location={location}
                          myLocationsDTO={myLocationsDTO}
                          setMyLocationsDTO={setMyLocationsDTO}
                      />
                    </div>
                )
              })}
            </div>
          </div>
        </main>
      </div>
  );
};

export default MyLocations;
