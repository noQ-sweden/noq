import React, { useEffect, useState } from "react";
import HostCardComponent from "../../components/HostCardComponent";
import { Button, Typography } from "@material-tailwind/react";
import { IVacanciesViewModel } from "./IVacanciesViewModel";
import {
  createReservation,
  getVacanciesView,
} from "../../../../api/VacanciesViewApi";
import { ModalComponent } from "../../components/ModalComponent";
import { MessageToClientComponent } from "../../components/MessageToClientComponent";
import { getReservation } from "../../../../api/ReservationsViewApi";

export default function VacanciesView() {
  const [open, setOpen] = useState(false);
  const [reservationResponse, setReservationResponse ] = useState(false)
  const [vacancies, setVacancies] = useState<IVacanciesViewModel>({
    vacancies: [],
  });

  const userId = "1";

  const fetchView = async () => {
    try {
      const response = await getVacanciesView();
      {
        if (response?.data) {
          setVacancies(response.data);
        }
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchView();
    checkIfUserHasAnyReservations(userId);
  }, []);

  async function makeReservation(hostId: string, bedId: string) {
    try {
      await createReservation(hostId, userId, bedId);
     setOpen(true)
    } catch (error) {
      console.error(error);
    }
    fetchView();
  }
  const checkIfUserHasAnyReservations = async (userId: string) => {
    try {
      const response = await getReservation(userId);
      if(response?.status === 200) {
        setReservationResponse(true);
      }
    } catch (error) {
      console.log(error)
    }
  }

  //********************************************************* 
  return (
    <>
      <div className="grid place-items-center ">
        <Typography variant="h2" color="blue-gray">
          Lediga sängplatser
        </Typography>
      {!reservationResponse ? <MessageToClientComponent text="Du har redan bokat en sängplats, vänligen invänta svar." color="red"></MessageToClientComponent>: null}

        {open ? (
            <ModalComponent open={open} setOpen={setOpen} />
        ) : null}
        {reservationResponse ? <div className="mt-12 grid gap-8 ">
          {vacancies.vacancies.map((vacancy) => (
            <div
              onClick={() => makeReservation(vacancy.host.id, vacancy.bedId)}
              key={vacancy.host.id}
            >
              {vacancy.bedId}
              <HostCardComponent
                key={vacancy.id}
                hostName={vacancy.host.name}
                address={vacancy.host.address}
                hostImg={vacancy.host.image}
              />
         
            </div>
          ))}
        </div> : null}
      </div>
    </>
  );
}
