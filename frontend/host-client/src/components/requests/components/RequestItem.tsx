"use client"

import {
  RequestsPageDTO,
  Reservation,
  Status,
  UpdateChangeType,
  UpdateReservationStatusField
} from "@/components/requests/RequestsPageDTO";
import React, {useState} from "react";
import {fetchUpdateReservationStatusField} from "@/components/requests/RequestsAPIHelper";

interface RequestItemProps {
  request: Reservation
  requestsViewModel: RequestsPageDTO
  setRequestsViewModel: (requestsViewModel: RequestsPageDTO) => void
}

const RequestItem = (props: RequestItemProps) => {
  const [isLoadingApprove, setIsLoadingApprove] = useState<boolean>(false);
  const [isLoadingDeny, setIsLoadingDeny] = useState<boolean>(false);

  const onDenyBtn = (reservationId: string) => {
    setIsLoadingDeny(true)
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      newValue: Status.DENIED,
      updateChangeType: UpdateChangeType.UPDATE_STATUS
    }
    fetchUpdateReservationStatusField(reqBody)
        .then(props.setRequestsViewModel)
        .finally(() => setIsLoadingDeny(false))
  }
  const onAcceptBtn = (reservationId: string) => {
    setIsLoadingApprove(true)
    const reqBody: UpdateReservationStatusField = {
      reservationId: reservationId,
      newValue: Status.APPROVED,
      updateChangeType: UpdateChangeType.UPDATE_STATUS
    }
    fetchUpdateReservationStatusField(reqBody)
        .then(props.setRequestsViewModel)
        .finally(() => setIsLoadingApprove(false))
  };

  return (
      <div key={props.request.id} className={"border-2 border-zinc-300 p-1 rounded"}>
        <p>Namn: {props.request.name}</p>
        <p>Status: {props.request.status}</p>
        <div className={"flex gap-1"}>
          <button onClick={() => onAcceptBtn(props.request.id)}
                  disabled={isLoadingDeny || props.request.status === Status.APPROVED}
                  className={`btn btn-outline btn-success btn-xs w-32 disabled:text-zinc-400`}>
            <p className={`${isLoadingApprove ? "hidden" : "block"}`}>Acceptera</p>
            <span
                className={`${isLoadingApprove ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
          </button>
          <button onClick={() => onDenyBtn(props.request.id)}
                  disabled={isLoadingDeny || props.request.status === Status.DENIED}
                  className="btn btn-outline btn-error btn-xs  w-20 disabled:text-zinc-400">
            <p className={`${isLoadingDeny ? "hidden" : "block"}`}>Neka</p>
            <span
                className={`${isLoadingDeny ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
          </button>
        </div>
      </div>
  )
}

export default RequestItem;
