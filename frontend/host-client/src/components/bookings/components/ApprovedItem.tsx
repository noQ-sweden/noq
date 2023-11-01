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
  reservation: Reservation
  requestsViewModel: RequestsPageDTO
  setRequestsViewModel: (requestsViewModel: RequestsPageDTO) => void
}

const ApprovedItem = (props: RequestItemProps) => {
  const [isLoadingApprove, setIsLoadingApprove] = useState<boolean>(false);
  const [isLoadingDeny, setIsLoadingDeny] = useState<boolean>(false);

  const onDelete = (reservationId: string) => {
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

  return (
      <div key={props.reservation.id} className={"border-2 border-zinc-300 p-1"}>
        <p>Namn: {props.reservation.name}</p>
        <p>Status: {props.reservation.status}</p>
        <div className={"flex gap-1"}>
          <button onClick={() => onDelete(props.reservation.id)} disabled={isLoadingDeny}
                  className="btn btn-outline btn-error btn-xs  w-20">
            <p className={`${isLoadingDeny ? "hidden" : "block"}`}>Ta bort</p>
            <span
                className={`${isLoadingDeny ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
          </button>
        </div>
      </div>
  )
}

export default ApprovedItem;
