import React, {useEffect, useState} from "react";
import {IRequestsViewModel, IReservation, Status} from "./IRequestsViewModel";
import {
    approveReservations,
    getAllHostRequests, rejectReservations
} from "../../../../api/RequestsViewApi";
import {useContext} from "react";
import {HostPageContext} from "../../../../context/HostPageContext";
import {Typography} from "@material-tailwind/react";
import UpdateBedsComponent from "../settingsView/components/UpdateBedsComponent";
import UpdateNameComponent from "../settingsView/components/UpdateNameComponent";
import UpdatePhotoComponent from "../settingsView/components/UpdatePhotoComponent";

export default function RequestsView() {
    const [requests, setRequests] = useState<IRequestsViewModel>({
        reservations: [],
    });
    const [checkedIds, setChckedIds] = useState<string[]>([]);
    const {id, name} = useContext(HostPageContext)


    const getAllRequests = async () => {
        try {
            const response = await getAllHostRequests(id);
            setRequests(response?.data);
        } catch (error) {
            console.error(error);
        }
    };


    useEffect(() => {
        getAllRequests();
    }, []);

    const handleApprove = async () => {
        try {
            await approveReservations(checkedIds, id);
        } catch (error) {
            console.error(error);
        }
        getAllRequests()
    };


    const handleReject = async () => {
        try {
            await rejectReservations(checkedIds, id);
        } catch (error) {
            console.error(error);
        }
        getAllRequests()
    };

    const toggleCheckbox = (id: string) => {
        if (checkedIds.includes(id)) {
            setChckedIds(checkedIds.filter((checkedIds) => checkedIds !== id));
        } else {
            setChckedIds([...checkedIds, id]);
        }
    };

    return (
        <>
            <main>
                <div className="container px-8 py-4 ">
                    <Typography variant="h2" color="blue-gray" className="py-2 flex justify-end">{name}</Typography>
                    <div className="px-8 py-4 ">

                        <Typography variant="h4" color="blue-gray" className="border-b-2 py-2">
                            Hantera förfrågningar
                        </Typography>
                    </div>

                    <div className="flex justify-start px-4  laptop:justify-self-start laptop:self-end">
                        <div className="p-5 max-w-sm flex flex-col gap-y-4 border-b-2">
                            <Typography>Förfrågningar:</Typography>
                            <div className="mt-2 min-w-xs">
                                {requests?.reservations.filter(request => request.status === "PENDING")
                                    .map((request) => (
                                        <div
                                            key={request.reservationId}
                                            className="flex items-center space-x-2 divide-y divide-lilac bg-gray mb-2"
                                        >
                                            <Typography>
                                                {request.user.name}
                                            </Typography>

                                            <input
                                                type="checkbox"
                                                checked={checkedIds.includes(request.reservationId)}
                                                onChange={() => toggleCheckbox(request.reservationId)}
                                            />
                                        </div>
                                    ))}
                            </div>
                            <div className="flex gap-5 justify-self-end">
                                <button
                                    onClick={handleApprove}
                                    className="bg-green text-white rounded px-5 py-2 mt-4"
                                >
                                    Godkänn
                                </button>
                                <button
                                    onClick={handleReject}
                                    className="bg-amber-500 text-white rounded p-2 mt-4"
                                >
                                    Neka
                                </button>
                            </div>
                        </div>

                    </div>
                    <div className="ml-4 my-2 w-64">
                        <div className="p-5 max-w-sm border-b-2">
                            <Typography>Godkända:</Typography>
                            <div className="mt-2">
                                {requests?.reservations.filter(request => request.status === "RESERVED")
                                    .map((request) => (
                                        <div
                                            key={request.reservationId}
                                            className="flex items-center space-x-2 mt-2 divide-y"
                                        >
                                            {request.user.name}
                                        </div>
                                    ))}
                            </div>
                        </div>

                        <div className="p-5 max-w-sm border-b-2">
                            <Typography>Nekade:</Typography>
                            <div className="mt-2">
                                {requests?.reservations.filter(request => request.status === "CANCELLED")
                                    .map((request) => (
                                        <div
                                            key={request.reservationId}
                                            className="flex items-center space-x-2 mt-2 divide-y bg-gray"
                                        >
                                            {request.user.name}
                                        </div>
                                    ))}
                            </div>
                        </div>
                    </div>
                </div>


            </main>
        </>
    );
}
