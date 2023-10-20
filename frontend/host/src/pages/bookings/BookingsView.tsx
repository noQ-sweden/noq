import React from 'react';
import Bookings from "./components/Bookings";
import BookingsViewProvider from "./BookingsViewContext";

const BookingsView = () => {
  return (
      <BookingsViewProvider>
        <Bookings/>
      </BookingsViewProvider>
  );
};

export default BookingsView;
