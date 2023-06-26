import Ibooking from "../../../interfaces/IBooking";
import { useContext } from "react";
import { UserContext } from "../../../App";
import { IHost } from "../../../interfaces/IHost";

interface IBookingCard {
  host: IHost;
  status: string;
}

function BookingCard({ host, status }: IBookingCard) {
  let cardColour;

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
      <div className="flex items-center justify-center py-8">
        <div className="flex items-center border rounded-md shadow-xl p-5">
          <div className="flex flex-col">
            <h4 className="text-xl font-semibold mb-2">{host.name}</h4>
            <span>{`${host.address.street} ${host.address.streetNum},`}</span>
            <span>{`${host.address.postalCode} ${host.address.cityName}`}</span>
            <span className="mt-2">20kr</span>
          </div>
          <div className="w-24 h-24 bg-gray-300 rounded-full ml-6"></div>
        </div>
      </div>
    </div>
  );
}
export default BookingCard;
