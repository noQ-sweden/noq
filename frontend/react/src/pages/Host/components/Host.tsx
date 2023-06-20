import {useEffect, useState} from "react";
import {getAllHostRequests} from "../../../api/GetAllHostRequests";
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import {IoSettingsSharp} from "react-icons/all";
import {FaRegCalendarAlt} from "react-icons/fa";
import {Checkbox, FormControlLabel, FormGroup} from "@mui/material";
import * as React from "react";
import {IReservation} from "../../../interfaces/IReservation";
import {approveReservations} from "../../../api/ApproveReservations";


export default function Host() {
    const [selectedBeds, setSelectedBeds] = useState(0);
    const [requests, setRequests] = useState<IReservation[]>([]);
    const [approvedIds, setApprovedIds] = useState<string[]>([]);

    const hostId = "4";

    const getAllRequests = async () => {
        try {
            const response = await getAllHostRequests(hostId);
            setRequests(response?.data);
            console.log(response?.data)
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        getAllRequests();
    }, []);

    const handleSave = () => {
        // put to backend w nr of beds
    };

    const handleApprove = async () => {
        try {
            await approveReservations(approvedIds, hostId)
        } catch (error) {
            console.error(error);
        }
    };

    const handleDecline = async () => {
        try {

        } catch (error) {
            console.error(error);
        }
    };

    const toggleCheckbox = (id: string) => {
        if (approvedIds.includes(id)) {
            setApprovedIds(approvedIds.filter((approvedId) => approvedId !== id));
        } else {
            setApprovedIds([...approvedIds, id]);
        }
    };

    return (
        <div className="grid grid-cols-4 h-screen">
            <aside style={{backgroundColor: '#AEC4FD'}} className="text-black py-10 px-4 col-span-1">
                <div className="flex items-center justify-center">
                    <nav aria-label="main bookings settings">
                        <List>
                            <ListItem disablePadding>
                                <ListItemButton>
                                    <ListItemIcon>
                                        <FaRegCalendarAlt style={{color: "black"}}/>
                                    </ListItemIcon>
                                    <ListItemText primary="Bokningar"/>
                                </ListItemButton>
                            </ListItem>
                            <ListItem disablePadding>
                                <ListItemButton>
                                    <ListItemIcon>
                                        <IoSettingsSharp style={{color: "black"}}/>
                                    </ListItemIcon>
                                    <ListItemText primary="Inställningar"/>
                                </ListItemButton>
                            </ListItem>
                        </List>
                    </nav>
                </div>
            </aside>

            <main className="py-10 px-4 col-span-3">

                <div className="flex items-center justify-center">
                    <div className="p-4">
                        <h2 className="text-2xl font-bold ">Antal lediga sängar:</h2>
                        <div className="mt-2">
                            <select
                                value={selectedBeds}
                                onChange={(e) => setSelectedBeds(Number(e.target.value))}
                                className="p-2 rounded border border-gray-300"
                            >
                                {[1, 2, 3, 4, 5].map((beds) => (
                                    <option key={beds} value={beds}>
                                        {beds}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <button
                            onClick={handleSave}
                            className="bg-blue-500 text-white rounded p-2 mt-4"
                        >
                            Spara
                        </button>

                        <h2 className="text-2xl font-bold mt-8">Förfrågningar:</h2>
                        <div className="mt-2">
                            {requests.map((request) => (
                                <div key={request.reservationId} className="flex items-center space-x-2 mt-2">
                                    <FormGroup>
                                        <FormControlLabel control={
                                            <Checkbox checked={approvedIds.includes(request.reservationId)}
                                                      onChange={() => toggleCheckbox(request.reservationId)}/>}
                                                          label={request.user.name}/>
                                    </FormGroup>
                                </div>
                            ))}
                        </div>
                        <button
                            onClick={handleApprove}
                            className="bg-green-500 text-white rounded p-2 mt-4"
                        >
                            Godkänn
                        </button>
                        <button
                            onClick={handleDecline}
                            className="bg-red-500 text-white rounded p-2 mt-4"
                        >
                            Neka
                        </button>
                    </div>
                </div>
            </main>
        </div>
    );
}
