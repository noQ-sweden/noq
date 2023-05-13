import Ibooking from "../interface/Booking";

function BookingCard({ booking }: { booking: Ibooking }) {
  const { status } = booking;
  let cardColour;
  /// Whoever makes ENUM-models for reservation status -
  /// make sure to read the model description in the google-drive folder.

  switch (status) {
    case "PENDING":
      cardColour = "bg-yellow-200";
      break;
    case "RESERVED":
      cardColour = "bg-green-200";
      break;
    case "CANCELLED":
      cardColour = "bg-red-200";
      break;
  }
  return (
    <div className={`p-4 ${cardColour}`}>
      <h2>{booking.name}</h2>
      <p>Datum: {booking.date}</p>
      <p>Adress: {booking.address}</p>
    </div>
  );
}
export default BookingCard;
