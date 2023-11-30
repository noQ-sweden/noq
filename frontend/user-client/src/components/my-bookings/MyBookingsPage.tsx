"use client"
import React, {useEffect, useState} from 'react';
import {MyBookingsPageDTO} from "@/components/my-bookings/MyBookingsPageDTO";
import BookingsItem from "@/components/my-bookings/components/BookingsItem";

interface RequestsProps {
  data: MyBookingsPageDTO
}

const MyBookingsPage = (props: RequestsProps) => {
  const [bookingViewModel, setBookingViewModel] = useState<MyBookingsPageDTO>(props.data);

  useEffect(() => {
    setBookingViewModel(props.data)
  }, []);
  console.log(bookingViewModel)
  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12"}>
            <div className={"flex flex-col gap-1"}>
              {bookingViewModel.bookings && bookingViewModel.bookings.map(request => {
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
          </div>
        </main>
      </div>
  );
};

export default MyBookingsPage;
