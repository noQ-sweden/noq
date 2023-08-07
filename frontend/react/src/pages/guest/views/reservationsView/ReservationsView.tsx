import { Typography } from "@material-tailwind/react";
import React, {useEffect, useState} from "react";
import HostCardComponent from "../../components/HostCardComponent";
import {IReservationsViewModel} from "./IReservationsViewModel";
import {getReservation, getReservationsView} from "../../../../api/ReservationsViewApi";

export default function ReservationsView() {
    const [reservation, setReservation] = useState<IReservationsViewModel>()
    const userId = "1"

    const fetchView = async () => {
        try {
            const response = await getReservation(userId);
            setReservation(response?.data);
            console.log(response?.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchView()
    }, [])


    return (
    <>
      <div>ReservationsView</div>
      <Typography>Status</Typography>
        <HostCardComponent onClick={} key={reservation.hostId}
                           hostId={reservation.hostId}
                           bedId={reservation.bedId}
                           hostName={reservation.hostName}
                           address={reservation.address}
                           hostImg={reservation.hostImg}
        />
    </>
  );
}
