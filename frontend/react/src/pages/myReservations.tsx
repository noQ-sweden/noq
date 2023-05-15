import BookingCard from "../components/bookingCard";
import Ibooking from "../interfaces/Booking";
function MyReservations() {
  const bookings: Ibooking[] = [
    {
      id: 1,
      name: "Stadsmissionen Östra",
      address: "Provgatan 1",
      date: "2023-05-14",
      status: "PENDING",
    },
    {
      id: 2,
      name: "Stadsmissionen Västra",
      address: "Någonstans 2",
      date: "2023-05-15",
      status: "RESERVED",
    },
    {
      id: 3,
      name: "Stadsmissionen Norra",
      address: "Hemligagtan 3",
      date: "2023-05-16",
      status: "CANCELLED",
    },
  ];
  const pendingBookings = bookings.filter(
    (booking) => booking.status === "PENDING"
  );
  const acceptedBookings = bookings.filter(
    (booking) => booking.status === "RESERVED"
  );
  const declinedBookings = bookings.filter(
    (booking) => booking.status === "CANCELLED"
  );

  return (
    <>
      <nav className=" h-16 container-3 flex bg-blue-200 text-2xl">
        <div className="flex items-center flex-1">
          <div className="flex-1 flex justify-center">Start</div>
          <div className="ml-auto text-white">Logo</div>
        </div>
      </nav>

      <header className="p-3 container-3 flex justify-center">
        <h1 className="text-3xl">Mina bokningar</h1>
      </header>
      <section className="flex container-3">
        <div className="bg-blue-200 flex flex-1 m-1 justify-center">
          Bekräftade
        </div>
        <div className="flex flex-1 m-1 justify-center border-grey border-2 ">
          Behandlas
        </div>
      </section>
      <main>
        <div className="grid grid-rows-3 gap-4">
          <div>
            <h2>Behandlas</h2>
            {pendingBookings.map((booking) => (
              <BookingCard key={booking.id} booking={booking} />
            ))}
          </div>
          <div>
            <h2>Bekräftade</h2>
            {acceptedBookings.map((booking) => (
              <BookingCard key={booking.id} booking={booking} />
            ))}
          </div>

          <div>
            <h2>Avslagna</h2>
            {declinedBookings.map((booking) => (
              <BookingCard key={booking.id} booking={booking} />
            ))}
          </div>
        </div>
      </main>
    </>
  );
}

export default MyReservations;
