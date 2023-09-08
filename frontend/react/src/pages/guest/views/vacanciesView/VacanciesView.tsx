import React, { useEffect, useState } from "react";
import HostCardComponent from "../../components/HostCardComponent";
import { Button, Typography } from "@material-tailwind/react";
import { IVacanciesViewModel } from "./IVacanciesViewModel";
import {
  createReservation,
  getVacanciesView,
} from "../../../../api/VacanciesViewApi";
import { ModalComponent } from "../../components/ModalComponent";

export default function VacanciesView() {

  const [vacancies, setVacancies] = useState<IVacanciesViewModel[]>([]);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [openModal, setOpenModal] = useState(false);
  const [open, setOpen] = useState(false);

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

  return (
    <>


      <div className="grid place-items-center ">
        <Typography variant="h2" color="blue-gray">
          Lediga sängplatser
        </Typography>
      


        {open ? (
            <ModalComponent open={open} setOpen={setOpen} />
        ) : null}
        <div className="mt-12 grid gap-8 ">
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
        </div>
      </div>
    </>
  );
}
