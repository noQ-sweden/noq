import {useEffect, useState} from "react";
import {getAllHostRequests} from "../../../../api/GetAllHostRequests";
import {IoMdSettings} from "react-icons/io";
import {FaRegCalendarAlt} from "react-icons/fa";
import {List, ListItem, ListItemPrefix} from "@material-tailwind/react";
import * as React from "react";
import {IReservation} from "../../../../interfaces/IReservation";
import {approveReservations} from "../../../../api/ApproveReservations";
import HandleBedsComponent from "../../components/HandleBedsComponent";


export default function BedsView() {

    return (
            <main className="py-10 px-4 col-span-3">
                <div className="flex items-center justify-center">
                    <div className="p-4">
                        <HandleBedsComponent></HandleBedsComponent>
                    </div>
                </div>
            </main>
    
    );
}