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
    <div className="bg-white rounded  p-4 m-4">
      <main className="flex flex-col gap-4">
        {isPending ? <LoadingSpinner /> : <>
          <section className="flex flex-col gap-4 border-t-2 border-zinc-500 p-4">
            <h1 className="text-lg font-semibold">Förfrågningar</h1>
            {data && data.pendingBookings.map((reservation: any) => {
              return (
                <div key={reservation.id} className="flex flex-col border max-w-md rounded-lg shadow-sm p-4 gap-4 bg-white">
                  <p className="text-gray-700">Namn: {reservation.name}</p>
                  <p className="text-gray-700">Unokod: {reservation.unoCode}</p>
                  <div className="flex flex-col sm:flex-row gap-2">
                    <Button1 title="Godkänn" isLoading={false} onClick={() => onBtnApprove(reservation.id, reservation.userId)} />
                    <Button1Error title="Neka" isLoading={false} onClick={() => onBtnDenied(reservation.id, reservation.userId)} />
                  </div>
                </div>
                )
              })}
            </section>
          </>}

            <section className={"flex flex-col gap-1 border-t-2 border-zinc-500 p-4"}>
              <h2 className={"text-lg font-semibold"}>Godkända</h2>

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
        </main>
      </div>
  );
};

export default Requests;
