import React from "react";
import HostCardComponent from "../../components/HostCardComponent";
import { Typography } from "@material-tailwind/react";

export default function VacanciesView() {
  return (
    <>
      <div className="grid place-items-center ">
        <Typography variant="h2" color="blue-gray">
          Lediga sängplatser
        </Typography>
        <div className="mt-12 grid gap-8 ">
          <HostCardComponent></HostCardComponent>
          <HostCardComponent></HostCardComponent>
          <HostCardComponent></HostCardComponent>
        </div>
      </div>
    </>
  );
}
