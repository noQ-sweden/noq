"use client"
import React, {useEffect, useState} from 'react';
import {MyBookingsPageDTO} from "@/components/my-bookings/MyBookingsPageDTO";
import BookingsItem from "@/components/my-bookings/components/BookingsItem";
import {fetchPage} from "@/components/my-bookings/MyBookingsPageAPI";
import LoadingSpinner from "@/libs/LoadingSpinner";

interface RequestsProps {
}

const MyBookingsPage = (props: RequestsProps) => {
  const [bookingViewModel, setBookingViewModel] = useState<MyBookingsPageDTO>();

  useEffect(() => {
    fetchPage("token").then(setBookingViewModel)
  }, []);

  console.log(bookingViewModel)
  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12"}>
            {bookingViewModel ? <>
              <div className={"flex flex-col gap-1"}>
                {bookingViewModel && bookingViewModel.bookings && bookingViewModel.bookings.map(request => {
                  return (
                      <div key={request.id}>
                        <BookingsItem booking={request}
                                      bookingViewModel={bookingViewModel}
                                      setBookingViewModel={setBookingViewModel}
                        />
                      </div>
                  )
                })}
              </div>
            </> : <LoadingSpinner/> }
          </div>
        </main>
      </div>
  );
};

export default MyBookingsPage;
