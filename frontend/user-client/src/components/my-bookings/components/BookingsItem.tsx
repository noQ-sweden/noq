"use client"

import React, { useEffect, useState } from "react";
import {Booking, MyBookingsPageDTO} from "@/components/my-bookings/MyBookingsPageDTO";

interface RequestItemProps {
  booking: Booking
  bookingViewModel: MyBookingsPageDTO
  setBookingViewModel: (requestsViewModel: MyBookingsPageDTO) => void
}

const BookingsItem = (props: RequestItemProps) => {
  const [status, setStatus] = useState("")


  const translateStatus = (status: string) => {
    const statusTranslations: { [key: string]: string } = {
      'PENDING': 'Väntande',
      'APPROVED': 'Godkänd',
      'CANCELED': 'Avbokad',
    };

    return statusTranslations[status] || status;
  };

  
    useEffect(() => {
      setStatus(translateStatus(props.booking.bookingStatus));
    }, [props.booking.bookingStatus]);

  return (
    <div
    key={props.booking.id}
    className="max-w-md rounded overflow-hidden shadow-lg bg-white p-4 m-4"
  >
    <div className="grid grid-cols-2 p-2">
      <div className="place-self-center">
        <h3 className="text-lg font-semibold">
        {props.booking.host.name}
        </h3>
        <p className="text-gray-700 text-base">
        {props.booking.host.address1}
        </p>
        <p className="text-gray-700 text-base">
        {props.booking.host.address2}
        </p>
      </div>
          <div className="text-center mt-4">
        <p>Status</p>
        <p>{status}</p>
        </div>
      </div>
    </div>
  )
}

export default BookingsItem;
