"use client"
import React from 'react';
import {BookingPageDTO, BookingReqBody} from "@/components/booking/BookingPageDTO";
import Button1 from "@/libs/Button1";
import {useRouter} from "next/navigation";
import {fetchSendHostRequest} from "@/components/booking/BookingPageAPI";

interface BookingProps {
  data: BookingPageDTO
}

const BookingPage = (props: BookingProps) => {
  const router = useRouter();

  const onClickSendHostRequest = (userId: string, hostId: string) => {
    const reqBody: BookingReqBody = {
      userId: userId,
      hostId: hostId,
    }
    fetchSendHostRequest(reqBody)
        .then(res => console.log(res))
        .finally(() => router.push("/booking/confirmation"))
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
            <Button1 title={"Skicka Förfrågan"} isLoading={false}
                     onClick={() => onClickSendHostRequest(props.data.userId, props.data.hostId)}/>
          </section>
        </div>
      </main>
  );
};

export default BookingPage;
