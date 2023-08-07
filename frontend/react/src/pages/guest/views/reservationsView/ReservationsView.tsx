import { Typography } from "@material-tailwind/react";
import React, {useEffect, useState} from "react";
import HostCardComponent from "../../components/HostCardComponent";
import {IReservationsViewModel} from "./IReservationsViewModel";
import {getReservation, getReservationsView} from "../../../../api/ReservationsViewApi";
import {createReservation} from "../../../../api/VacanciesViewApi";

export default function ReservationsView() {
    const [reservation, setReservation] = useState<IReservationsViewModel>({
        reservationId: "",
        hostName: "",
        hostImage: "",
        address: {
            id: "",
            street: "",
            streetNum: "",
            postalCode: "",
            cityName: ""
        }
    });
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

    async function makeReservation( hostId: string, bedId: string) {
        console.log(bedId, hostId)
        try {
            await createReservation(hostId, userId, bedId)
        } catch (error) {
            console.error(error);
        }
        fetchView()
    }


    return (
    <>
      <div>ReservationsView</div>
      <Typography>Status</Typography>
        <HostCardComponent key={reservation?.reservationId}
                           hostName={reservation.hostName}
                           hostImg={reservation.hostImage}
                           address={reservation.address}
        />
    </>
  );
}
