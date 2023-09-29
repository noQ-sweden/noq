import { Typography } from "@material-tailwind/react";
import {useEffect, useState} from "react";
import HostCardComponent from "../../components/HostCardComponent";
import {IReservationsViewModel} from "./IReservationsViewModel";
import {getReservation} from "../../../../api/ReservationsViewApi";

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
    const clientId = "1"

    const fetchView = async () => {
        try {
            const response = await getReservation(clientId);
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

      <Typography>Status</Typography>
        {reservation ? (
            <HostCardComponent
                key={reservation.reservationId}
                hostName={reservation.hostName}
                hostImg={reservation.hostImage}
                address={reservation.address}
            />
        ) : (
            <Typography>Du har inte bokat nått ännu</Typography>
        )}
    </>
  );
}
