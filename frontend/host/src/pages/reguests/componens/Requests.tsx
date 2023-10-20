import React, {useContext, useEffect, useState} from 'react';
import {RequestsViewContext, requestsViewModelEmptyData} from "../RequestsViewContext";
import {RequestsViewModel, UpdateChangeType, UpdateReservationStatusField} from "../RequestsViewModel";
import {fetchUpdateReservationStatusField} from "../RequestsViewAPI";
import {useMutation} from "@tanstack/react-query";

const Requests = () => {
  const requestsViewModelContext = useContext<RequestsViewModel>(RequestsViewContext);

  const [requestsViewModel, setRequestsViewModel] = useState<RequestsViewModel>(requestsViewModelEmptyData);

  useEffect(() => {
    setRequestsViewModel(requestsViewModelContext)
  }, []);

  const fetchUpdateReservationStatusFieldMutate = useMutation({
    mutationFn: (reqBody: UpdateReservationStatusField) => fetchUpdateReservationStatusField(reqBody),
    onSuccess: (data) => {
      console.log(data)
    },
  })

  const onDeleteBtn = (reservationId: string, updateChangeType: UpdateChangeType) => {
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      updateChangeType: updateChangeType
    }
    fetchUpdateReservationStatusFieldMutate.mutate(reqBody)
  };

  const onAcceptBtn = (reservationId: string, updateChangeType: UpdateChangeType) => {
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      updateChangeType: updateChangeType
    }
    fetchUpdateReservationStatusFieldMutate.mutate(reqBody)
  };

  return (
      <main className={"flex justify-center"}>
        <div className={"flex flex-col w-11/12"}>
          <p className={"text-xl font-bold"}>Förfrågningar</p>
          <div className={"flex flex-col gap-1"}>
            {requestsViewModel.reservations.map(request => {
              return (
                  <div key={request.id} className={"border-2 p-1"}>
                    <p>Namn: {request.name}</p>
                    <p>Status: {request.status}</p>
                    <div className={"flex gap-1"}>
                      <button onClick={() => onAcceptBtn(request.id, UpdateChangeType.ACCEPT)}
                              className="btn btn-outline btn-success btn-xs">
                        Acceptera
                      </button>
                      <button onClick={() => onDeleteBtn(request.id, UpdateChangeType.DENY)}
                              className="btn btn-outline btn-error btn-xs">
                        Neka
                      </button>
                    </div>
                  </div>
              )
            })}
          </div>
        </div>
      </main>
  );
};

export default Requests;
