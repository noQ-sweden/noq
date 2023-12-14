"use client"
import React, {useEffect, useState} from 'react';
import {RequestsPageDTO} from "@/components/requests/RequestsPageDTO";
import {fetchPage} from "@/components/requests/RequestsAPI";
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

  function onBtnDenied(id: string, userId: string) {
    console.log("onBtnDenied");
    console.log(id);
    console.log(userId);
  }

  function onBtnApprove(id: string, userId: string) {
    console.log("onBtnApprove");
    console.log(id);
    console.log(userId);
  }

  return (
      <div>
        <main className={"flex flex-col gap-1 border-zinc-500 md:mr-5 p-1"}>
          {isPending ? <LoadingSpinner/> : <>

            <section className={"flex flex-col gap-1"}>
              <h1 className={"text-2xl"}>Förfrågningar</h1>
              {data && data.pendingBookings.map((reservation: any) => {
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

            <section className={"flex flex-col gap-1 border-2 rounded border-zinc-500 p-4"}>
              <h1 className={"text-2xl"}>Godkända</h1>

              {data && data.approvedBookings.map((reservation: any) => {
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
              {data && data.disapprovedBookings.map((reservation: any) => {
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
