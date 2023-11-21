"use client"
import React from 'react';
import {BookingPageDTO, bookingPageMock} from "@/components/booking/BookingPageDTO";
import Button1 from "@/libs/Button1";
import {useRouter} from "next/navigation";
import SelectedHostItem from "@/components/booking/components/SelectedHostItem";
import Link from "next/link";

interface BookingProps {
  data: BookingPageDTO
}

const BookingPage = (props: BookingProps) => {
  console.log(props.data)
  const router = useRouter();

  const onClickSendHostRequest = (id: string, hostId: string) => {
    console.log(id)
    console.log(hostId)
  };

  return (
      <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
        <div className={"flex flex-col w-11/12 gap-1"}>

          <section>
            <Button1 title={"Tillbaka"} isLoading={false} onClick={() => router.back()}/>
          </section>

          <section>
            <p className={"font-bold"}>Välj önskat boställe</p>
            <p>Välj boställe i listan för att fråga om en sängplats för natten</p>
          </section>

          <section className={""}>
            <div className={"border-2 border-zinc-300 p-1 rounded"}>
              <div className={"flex flex-col"}>
                <p>{props.data.name}</p>
                <p>{props.data.address1}</p>
                <p>{props.data.address2}</p>
                <p>{props.data.countOfAvailablePlaces} / {props.data.totalAvailablePlaces}</p>
              </div>
            </div>
          </section>

          <section>
            <Button1 title={"Skicka Förfrågan"} isLoading={false} onClick={() => onClickSendHostRequest(props.data.id, props.data.hostId)}/>
          </section>
        </div>
      </main>
  );
};

export default BookingPage;
