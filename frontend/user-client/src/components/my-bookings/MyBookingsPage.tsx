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

  return (
      <div>
        <main className={"flex mt-1 xxs:justify-center md:justify-start"}>
          <div className={"flex flex-col w-11/12"}>
            <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4 mb-4">
              <div className="flex">
                <div className="flex-shrink-0">
                  <svg className="h-5 w-5 text-yellow-400" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd"/>
                  </svg>
                </div>
                <div className="ml-3">
                  <p className="text-sm text-yellow-700">
                    The shelter booking feature is temporarily disabled for the demo and soft launch.
                  </p>
                </div>
              </div>
            </div>
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
