import React, { useEffect, useState } from "react";
import {IRequestsViewModel} from "./IRequestsViewModel";
import {approveReservations, getAllHostRequests, getApprovedReservations} from "../../../../api/RequestsViewApi";

export default function RequestsView() {
  const [requests, setRequests] = useState<IRequestsViewModel[]>([]);
  const [approved, setApproved] = useState<IRequestsViewModel[]>([]);
  const [approvedIds, setApprovedIds] = useState<string[]>([]);

  const hostId = "host3";

  const getAllRequests = async () => {
    try {
      const response = await getAllHostRequests(hostId);
      setRequests(response?.data);
      console.log(response?.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    getAllRequests();
  }, []);

  const handleApprove = async () => {
    try {
      await approveReservations(approvedIds, hostId);
    } catch (error) {
      console.error(error);
    }
    getAllRequests()
    getApproved()
  };

  const getApproved = async () => {
    try {
      const response = await getApprovedReservations(hostId);
      setApproved(response?.data);
      console.log(response?.data);
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
    <>
      <main className="py-10 px-4 col-span-3">
        <div className="flex items-center justify-center">
          <div className="p-4">
            <h2 className="text-2xl font-bold mt-8">Förfrågningar:</h2>
            <div className="mt-2">
              {requests.map((request) => (
                  <div
                      key={request.reservationId}
                      className="flex items-center space-x-2 mt-2"
                  >
                    {request.user.name}
                    <input
                        type="checkbox"
                        checked={approvedIds.includes(request.reservationId)}
                        onChange={() => toggleCheckbox(request.reservationId)}
                    />
                  </div>
              ))}
            </div>
            <button
                onClick={handleApprove}
                className="bg-green text-white rounded p-2 mt-4"
            >
              Godkäsnn
            </button>
            <button
                onClick={handleDecline}
                className="bg-amber-500 text-white rounded p-2 mt-4"
            >
              Neka
            </button>
          </div>
        </div>
        <div className="flex items-center justify-center">
          <div className="p-4">
            <h2 className="text-2xl font-bold mt-8">Godkända:</h2>
            <div className="mt-2">
              {approved.map((request) => (
                  <div
                      key={request.reservationId}
                      className="flex items-center space-x-2 mt-2"
                  >
                    {request.user.name}
                  </div>
              ))}
            </div>
          </div>
        </div>
        <div className="flex items-center justify-center">
          <div className="p-4">
            <h2 className="text-2xl font-bold mt-8">Nekade:</h2>
            <div className="mt-2">
              {approved.map((request) => (
                  <div
                      key={request.reservationId}
                      className="flex items-center space-x-2 mt-2"
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
