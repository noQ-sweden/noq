import * as React from "react";
import HandleBedsComponent from "../components/HandleBedsComponent";
import UpdateInfoComponent from "../components/UpdateInfoComponent";
import { Button, Input, Typography } from "@material-tailwind/react";
import { IconBase } from "react-icons";
import { useState } from "react";

export default function BedsView() {
  const [username, setUsername] = useState("");


  const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };



  const hostId = "host4";

  const handleSave = async () => {
    try {
     // put username to server
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <main className="">
      <div className="container px-8 py-4 ">
        <div className="px-8 py-4 ">
          <IconBase></IconBase>
          <Typography variant="h4" color="blue-gray" className="border-b-2 py-2">
            Inställningar
          </Typography>
        </div>
        <div className="grid grid-flow-row px-4 py-4 my-2 gap-y-10 laptop:grid-flow-col laptop:grid-rows-2 laptop:grid-cols-auto laptop:gap-x-10">
          <div className="flex justify-start px-4  laptop:justify-self-start laptop:self-end">
            <HandleBedsComponent></HandleBedsComponent>
          </div>
          <div className="ml-4 my-2 w-64">
            <Typography className="mb-4">Uppdatera namn:</Typography>
            <Input
              size="md"
              label="Ändra namn"
              value={username}
              onChange={handleUsernameChange}
              crossOrigin={undefined}
              
            />
            <Button color="blue" onClick={handleSave} fullWidth className="my-2">Spara</Button>
          </div>
          <UpdateInfoComponent></UpdateInfoComponent>
        </div>
      </div>
    </main>
  );
}
