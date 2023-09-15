import * as React from "react";
import { useState } from "react";
import { putNrBeds } from "../../../../api/BedsViewApi";
import { Button, Select, Typography, Option } from "@material-tailwind/react";

export default function HandleBedsComponent() {
  const [selectedBeds, setSelectedBeds] = useState<number>(0);

  const hostId = "host4";

  const handleSave = async () => {
    try {
      await putNrBeds(selectedBeds, hostId);
    } catch (err) {
      console.log(err);
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
        <Button color="blue" onClick={handleSave} fullWidth>Spara</Button>
        </div>
      </div>
    </>
  );
}
