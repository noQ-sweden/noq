import React, { useEffect, useState } from "react";
import HostCardComponent from "../../components/HostCardComponent";
import { Typography } from "@material-tailwind/react";
import { IVacanciesViewModel } from "./IVacanciesViewModel";
import {
  createReservation,
  getVacanciesView,
} from "../../../../api/VacanciesViewApi";

export default function VacanciesView() {
  const [vacancies, setVacancies] = useState<IVacanciesViewModel>({vacancies: []});
  const userId = "1";

  const fetchView = async () => {
    try {
      const response = await getVacanciesView();
      {
        if (response?.data) {
          setVacancies(response.data);
          console.log(response?.data);
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
    console.log(bedId, hostId);
    try {
      await createReservation(hostId, userId, bedId);
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
        <div className="mt-12 grid gap-8 ">
          {vacancies?.vacancies.map((vacancy) => (
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
