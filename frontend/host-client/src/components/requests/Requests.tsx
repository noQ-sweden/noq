"use client"
import React, {useEffect, useState} from 'react';
import {BookingReqBody, RequestsPageDTO} from "@/components/requests/RequestsPageDTO";
import {fetchApproveRequest, fetchDenyRequest, fetchPage} from "@/components/requests/RequestsAPI";
import Button1 from "@/libs/Button1";
import Button1Error from "@/libs/ButtonError";
import {useQuery} from "@tanstack/react-query";
import LoadingSpinner from "@/libs/LoadingSpinner";

interface RequestsProps {
}

const Requests = (props: RequestsProps) => {
  const {isPending, isError, data, error} = useQuery({
    queryKey: [''],
    queryFn: () => {
      return fetchPage("", "hostId")
    },
  })

  const [requestsPageDTO, setRequestsPageDTO] = useState<RequestsPageDTO>(data)

  useEffect(() => {
    setRequestsPageDTO(data)
  }, [data]);

  const onBtnDenied = (reservationId: string, userId: string) => {
    const bookingReqBody: BookingReqBody = {
      hostId: "",
      bookingId: reservationId
    }
    fetchDenyRequest("", bookingReqBody).then(setRequestsPageDTO)

  };

  const onBtnApprove = (reservationId: string, userId: string) => {
    const bookingReqBody: BookingReqBody = {
      hostId: "",
      bookingId: reservationId
    }
    fetchApproveRequest("", bookingReqBody).then(setRequestsPageDTO)
  };

  return (
      <div>
        <main className={"flex flex-col gap-1 border-zinc-500 md:mr-5 p-1"}>
          {isPending ? <LoadingSpinner/> : <>
            <section>
              <p className={"text-2xl"}>2/10</p>
            </section>
            <section className={"flex flex-col gap-1 border-2 rounded border-zinc-500 p-4"}>
              <h1 className={"text-2xl"}>Godkända</h1>

              {requestsPageDTO && requestsPageDTO.approvedBookings.map((reservation: any) => {
                return (
                    <div key={reservation.id} className={"border-2 rounded border-zinc-500 p-1"}>
                      <p>Namn: {reservation.name}</p>
                      <p>Unokod: {reservation.unoCode}</p>
                    </div>
                )
              })}
            </section>

            <section className={"flex flex-col gap-1 border-2 rounded border-zinc-500 p-4"}>
              <h1 className={"text-2xl"}>Nekade</h1>
              {requestsPageDTO && requestsPageDTO.disapprovedBookings.map((reservation: any) => {
                return (
                    <div key={reservation.id} className={"border-2 rounded border-zinc-500 p-1"}>
                      <p>Namn: {reservation.name}</p>
                      <p>Unokod: {reservation.unoCode}</p>
                    </div>
                )
              })}
            </section>

            <section className={"flex flex-col gap-1"}>
              <h1 className={"text-2xl"}>Förfrågningar</h1>
              {requestsPageDTO && requestsPageDTO.pendingBookings.map((reservation: any) => {
                return (
                    <div key={reservation.id} className={"flex flex-col border-2 rounded border-zinc-500 p-1 gap-1"}>
                      <p>Namn: {reservation.name}</p>
                      <p>Unokod: {reservation.unoCode}</p>
                      <div className={"flex flex-col gap-1 sm:flex-row"}>
                        <Button1 title={"Godkänn"} isLoading={false}
                                 onClick={() => onBtnApprove(reservation.id, reservation.userId)}/>
                        <Button1Error title={"Neka"} isLoading={false}
                                      onClick={() => onBtnDenied(reservation.id, reservation.userId)}/>
                      </div>
                    </div>
                )
              })}
            </section>
          </>}

        </main>
      </div>
  );
};

export default Requests;
