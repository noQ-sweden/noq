import {useContext, useEffect, useState} from "react";
import {UserContext} from "../../App";
import axios from "axios";
import BookingCard from "./components/BookingCard";
import {IHost} from "../../interfaces/IHost";
import {getReservation} from "../../api/GetReservation";
import ClientViewHeader from "../../components/ClientViewHeader";

function MyReservations() {
    const [status, setStatus] = useState("");
    const [host, setHost] = useState<IHost>({
        address: {
            cityName: "",
            id: "",
            postalCode: "",
            street: "",
            streetNum: "",
        },
        beds: 0,
        hostId: "",
        image: "",
        name: "",
    });
    const {userId} = useContext(UserContext);

    const fetchReservation = async (userId: string | undefined) => {
        try {
            const response = await getReservation(userId)

            setStatus(response?.data.status);
            setHost(response?.data.host);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchReservation(userId);
    }, []);

    return (
        <>
            <ClientViewHeader></ClientViewHeader>
            <BookingCard key={host?.hostId} host={host} status={status}/>
        </>
    );
}

export default MyReservations;
