import React, {useEffect, useState} from "react";
import HostCardComponent from "../../components/HostCardComponent";
import {Typography} from "@material-tailwind/react";
import {IVacanciesViewModel} from "./IVacanciesViewModel";
import {createReservation, getVacanciesView} from "../../../../api/VacanciesViewApi";

export default function VacanciesView() {
    const [vacancies, setVacancies] = useState<IVacanciesViewModel[]>()
    const userId = "1"

    const fetchView = async () => {
        try {
            const response = await getVacanciesView();
            setVacancies(response?.data);
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
            <div className="grid place-items-center ">
                <Typography variant="h2" color="blue-gray">
                    Lediga sängplatser
                </Typography>
                <div className="mt-12 grid gap-8 ">
                    {vacancies?.map(vacancy => (
                        <div onClick={() => makeReservation(vacancy.hostId, vacancy.bedId)} key={vacancy.hostId}>
                            {vacancy.bedId}
                            <HostCardComponent key={vacancy.id}
                                           hostName={vacancy.hostName}
                                           address={vacancy.address}
                                           hostImg={vacancy.hostImg}
                        />
                        </div>
                    ))}
                </div>
            </div>
        </>
    );
}
