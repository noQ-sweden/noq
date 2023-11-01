"use client"
import React, {useEffect, useState} from 'react';
import {BookingsPageDTO} from "@/components/bookings/BookingsPageDTO";
import ApprovedItem from "@/components/bookings/components/ApprovedItem";

interface BookingsProps {
  data: BookingsPageDTO
}

const Bookings = (props: BookingsProps) => {
  const [bookingsPageDTO, setBookingsPageDTO] = useState<BookingsPageDTO>(props.data);

  useEffect(() => {
    setBookingsPageDTO(props.data)
  }, []);

  return (
      <div>
        <main className={"flex justify-center"}>
          <div className={"flex flex-col w-11/12"}>
            <p className={"text-xl font-bold"}>Bokningar</p>
            <div className={"flex flex-col gap-1"}>
              {bookingsPageDTO.reservations && bookingsPageDTO.reservations.map(request => {
                return (
                    <div key={request.id}>
                      <ApprovedItem reservation={request}
                                    requestsViewModel={bookingsPageDTO}
                                    setRequestsViewModel={setBookingsPageDTO}
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

export default Bookings;
