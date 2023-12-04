"use client"

import React from "react";
import {Booking, MyBookingsPageDTO} from "@/components/my-bookings/MyBookingsPageDTO";

interface RequestItemProps {
  booking: Booking
  bookingViewModel: MyBookingsPageDTO
  setBookingViewModel: (requestsViewModel: MyBookingsPageDTO) => void
}

const BookingsItem = (props: RequestItemProps) => {

  return (
      <div key={props.booking.id} className={"border-2 border-zinc-300 p-1 rounded"}>
        <div className={"flex flex-col"}>
          <p>{props.booking.bookingStatus}</p>
          <p>{props.booking.host.name}</p>
          <p>{props.booking.host.address1}</p>
          <p>{props.booking.host.address2}</p>
          <p>{props.booking.host.countOfAvailablePlaces} / {props.booking.host.totalAvailablePlaces}</p>
        </div>
      </div>
  )
}

export default BookingsItem;
