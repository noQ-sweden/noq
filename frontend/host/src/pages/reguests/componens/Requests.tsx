import React, {useContext, useEffect, useState} from 'react';
import {RequestsViewContext, requestsViewModelEmptyData} from "../RequestsViewContext";
import {
  RequestsViewModel,
  Reservation,
  Status,
  UpdateChangeType,
  UpdateReservationStatusField
} from "../RequestsViewModel";
import {fetchUpdateReservationStatusField} from "../RequestsViewAPI";
import {useMutation} from "@tanstack/react-query";

interface RequestItemProps {
  request: Reservation
  requestsViewModel: RequestsViewModel
  setRequestsViewModel: (requestsViewModel: RequestsViewModel) => void
}

const RequestItem = (props: RequestItemProps) => {
  const fetchOnAcceptBtnMutate = useMutation({
    mutationFn: (reqBody: UpdateReservationStatusField) => fetchUpdateReservationStatusField(reqBody),
    onSuccess: props.setRequestsViewModel
  })

  const fetchOnDenyBtnMutate = useMutation({
    mutationFn: (reqBody: UpdateReservationStatusField) => fetchUpdateReservationStatusField(reqBody),
    onSuccess: props.setRequestsViewModel
  })

  const onDenyBtn = (reservationId: string) => {
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      newValue: Status.DENIED,
      updateChangeType: UpdateChangeType.UPDATE_STATUS
    }
    fetchOnDenyBtnMutate.mutate(reqBody)
  };

  const onAcceptBtn = (reservationId: string) => {
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      newValue: Status.APPROVED,
      updateChangeType: UpdateChangeType.UPDATE_STATUS
    }
    fetchOnAcceptBtnMutate.mutate(reqBody)
  };

  return (
      <div key={props.request.id} className={"border-2 p-1"}>
        <p>Namn: {props.request.name}</p>
        <p>Status: {props.request.status}</p>
        <div className={"flex gap-1"}>
          <button onClick={() => onAcceptBtn(props.request.id)} disabled={fetchOnAcceptBtnMutate.isPending}
                  className={`btn btn-outline btn-success btn-xs w-32`}>
            <p className={`${fetchOnAcceptBtnMutate.isPending ? "hidden" : "block"}`}>Acceptera</p>
            <span
                className={`${fetchOnAcceptBtnMutate.isPending ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
          </button>
          <button onClick={() => onDenyBtn(props.request.id)} disabled={fetchOnDenyBtnMutate.isPending}
                  className="btn btn-outline btn-error btn-xs  w-20">
            <p className={`${fetchOnDenyBtnMutate.isPending ? "hidden" : "block"}`}>Neka</p>
            <span
                className={`${fetchOnDenyBtnMutate.isPending ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
          </button>
        </div>
      </div>
  )
}

const Requests = () => {
  const requestsViewModelContext = useContext<RequestsViewModel>(RequestsViewContext);

  const [requestsViewModel, setRequestsViewModel] = useState<RequestsViewModel>(requestsViewModelEmptyData);

  useEffect(() => {
    setRequestsViewModel(requestsViewModelContext)
  }, []);

  return (
      <main className={"flex justify-center"}>
        <div className={"flex flex-col w-11/12"}>
          <p className={"text-xl font-bold"}>Förfrågningar</p>
          <div className={"flex flex-col gap-1"}>
            {requestsViewModel.reservations.map(request => {
              return (
                  <div key={request.id}>
                    <RequestItem request={request}
                                 requestsViewModel={requestsViewModel}
                                 setRequestsViewModel={setRequestsViewModel}
                    />
                  </div>
              )
            })}
          </div>
        </div>
      </main>
  );
};

export default Requests;
