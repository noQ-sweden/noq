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
            <p className={"font-bold m-4"}>
                Vill du skicka förfrågan om sänmgplats till följande boede?
              </p>
            </section>

            <section className={""}>
            <div className="max-w-md rounded overflow-hidden shadow-lg bg-white p-4 m-4">
                <div className="grid grid-cols-2 p-2">
                  <div className="place-self-center">
                    <h3 className="text-lg font-semibold">
                      {bookingPageDTO.name}
                    </h3>
                    <p className="text-gray-700 text-base">
                      {bookingPageDTO.address1}
                    </p>
                    <p className="text-gray-700 text-base">
                      {bookingPageDTO.address2}
                    </p>
                  </div>
                </div>

              </div>
            </section>

            <section className="mt-4">
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
