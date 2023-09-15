import * as React from "react";
import {useContext, useState} from "react";
import { putNrBeds } from "../../../../../api/SettingsViewApi";
import { Button, Select, Typography, Option } from "@material-tailwind/react";
import {HostPageContext} from "../../../../../context/HostPageContext";

export default function UpdateBedsComponent() {
  const [selectedBeds, setSelectedBeds] = useState<number>(0);
  const [error, setError] = useState("");

  const {hostId} = useContext(HostPageContext)

  const handleSave = async () => {
    try {
      await putNrBeds(selectedBeds, hostId);
    } catch (err) {
      setError(err.message || "Couldn't update number of beds");
    }
  };

  return (
    <>
      <div className="flex flex-col">
        <Typography>Uppdatera antal lediga sängplatser:</Typography>
        <div className="mt-4">
        <Select defaultValue={selectedBeds}
            onChange={(e) => setSelectedBeds(Number(e))}>
                  {[1, 2, 3, 4, 5].map((beds) => (
              <Option key={beds} defaultValue={beds}>
                {beds}
              </Option>
            ))}
        </Select>
        </div>
        <div className="mt-2">
          {
              error && <div className="text-red-500">{error}</div>
          }
        <Button color="blue" onClick={handleSave} fullWidth>Spara</Button>
        </div>
      </div>
    </>
  );
}
