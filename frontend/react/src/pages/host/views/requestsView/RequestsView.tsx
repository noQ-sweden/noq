import React, {useEffect, useState} from "react";
import {IRequestsViewModel, IReservation, Status} from "./IRequestsViewModel";
import {
    approveReservations,
    getAllHostRequests, rejectReservations
} from "../../../../api/RequestsViewApi";
import {useContext} from "react";
import {HostPageContext} from "../../../../context/HostPageContext";
import {Typography} from "@material-tailwind/react";

export default function RequestsView() {
    const [requests, setRequests] = useState<IRequestsViewModel>({
        reservations: [],
    });
    const [checkedIds, setChckedIds] = useState<string[]>([]);
    const {hostId} = useContext(HostPageContext)


    const getAllRequests = async () => {
        try {
            const response = await getAllHostRequests(hostId);
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
            await approveReservations(checkedIds, hostId);
        } catch (error) {
            console.error(error);
        }
        getAllRequests()
    };


    const handleReject = async () => {
        try {
            await rejectReservations(checkedIds, hostId);
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
            <main className="p-10 px-4 col-span-3 ">
                <h1 className="text-4xl tracking-wide ">Hantera förfrågningar om sängplats</h1>
                <div className="grid grid-rows-3 gap-5 p-10">
                    <div className="p-5 max-w-sm h-64 min-h-full rounded-xl border border-lilac flex flex-col gap-y-4 ">
                        <h2 className="text-2xl font-medium">Förfrågningar</h2>
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
                    <div className="p-5 max-w-sm rounded-xl border border-lilac">

                        <h2 className="text-2xl font-medium">Godkända:</h2>
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
                    <div className="p-5 max-w-sm rounded-xl border border-lilac">
                        <h2 className="text-2xl font-medium">Nekade:</h2>
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
            </main>
        </>
    );
}
