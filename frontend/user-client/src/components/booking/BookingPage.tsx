"use client"
import React, {useEffect, useState} from 'react';
import {BookingPageDTO, BookingReqBody} from "@/components/booking/BookingPageDTO";
import Button1 from "@/libs/Button1";
import {useRouter} from "next/navigation";
import {fetchPage, fetchSendHostRequest} from "@/components/booking/BookingPageAPI";
import LoadingSpinner from "@/libs/LoadingSpinner";

interface BookingProps {
  id: string
}

const BookingPage = (props: BookingProps) => {
  const router = useRouter();
  const [bookingPageDTO, setBookingPageDTO] = useState<BookingPageDTO>()

  useEffect(() => {
    fetchPage("", props.id).then(setBookingPageDTO)
  }, [])

  const onClickSendHostRequest = (userId: string, hostId: string) => {
    const reqBody: BookingReqBody = {
      userId: userId,
      hostId: hostId,
    }
    fetchSendHostRequest(reqBody)
        .finally(() => router.push("/booking/confirmation"))
  };

  return (
      <main className={"flex mt-1 xxs:justify-center md:justify-start"}>

        {bookingPageDTO ? <>
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
                  <p>{bookingPageDTO.name}</p>
                  <p>{bookingPageDTO.address1}</p>
                  <p>{bookingPageDTO.address2}</p>
                  <p>{bookingPageDTO.countOfAvailablePlaces} / {bookingPageDTO.totalAvailablePlaces}</p>
                </div>
              </div>
            </section>

            <section>
              <Button1 title={"Skicka Förfrågan"} isLoading={false}
                       onClick={() => onClickSendHostRequest(bookingPageDTO.userId, bookingPageDTO.hostId)}/>
            </section>
          </div>
        </> : <>
          <LoadingSpinner/>
        </>}

      </main>
  );
};

export default BookingPage;
