import React from "react";

import {
  Button,
  Dialog,
  DialogBody,
  DialogFooter,
} from "@material-tailwind/react";
import { MessageToClientComponent } from "./MessageToClientComponent";
import {Link} from 'react-router-dom'

interface IModalComponents {
    open:boolean;
    setOpen: React.Dispatch<React.SetStateAction<boolean>>;

}


export function ModalComponent({open, setOpen}: IModalComponents) {
     
  const handleOpen = () => setOpen(!open);
 
  return (
    <>
      <Dialog
        open={open}
        handler={handleOpen}
        animate={{
          mount: { scale: 1, y: 0 },
          unmount: { scale: 0.9, y: -100 },
        }}
      >
        
        <DialogBody style={{display:"flex", alignItems:"center", justifyContent: "center"}} divider>
        <MessageToClientComponent  color={"green"} text={"Bokningen mottagen"}/>
        </DialogBody>
        <DialogFooter className="flex justify-center">
          <Link to={"/guest/:guestId/reservations"}>
          <Button variant="text" color="blue" onClick={handleOpen}>
            <span>Confirm</span>
          </Button>
            </Link>
       
        </DialogFooter>
      </Dialog>
    </>
  );
}