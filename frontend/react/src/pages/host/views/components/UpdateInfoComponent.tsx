import { Button, Input, Typography } from "@material-tailwind/react";
import React, { ChangeEvent, useState } from "react";

export default function UpdateInfoComponent() {


  return (
    <div className="flex items-center flex-col min-w-min max-w-xs gap-y-8">
      <div className="w-32 h-32 bg-gray-400 rounded-full overflow-hidden">
        <img
          src="/images/host4.jpg"
          alt="User Profile"
          className="w-full h-full object-cover"
        />
      </div>
      <div className="flex flex-cols gap-y-4">

        <Button color="blue">Ladda upp ny bild</Button>
      </div>
    </div>
  );
}
