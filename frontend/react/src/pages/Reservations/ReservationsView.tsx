import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../App";
import axios from "axios";
import BookingCard from "./components/BookingCard";
import { IHost } from "../../interfaces/IHost";
import { getReservation } from "../../api/GetReservation";
function MyReservations() {
  const [status, setStatus] = useState("");
  const [host, setHost] = useState<IHost>({
    address: {
      cityName: "",
      id: "",
      postalCode: "",
      street: "",
      streetNum: "",
    },
    beds: 0,
    hostId: "",
    image: "",
    name: "",
  });
  const { userId } = useContext(UserContext);

  const fetchReservation = async (userId: string | undefined) => {
    try {
      const response = await getReservation(userId)

      setStatus(response?.data.status);
      setHost(response?.data.host);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchReservation(userId);
  }, []);

  return (
    <>
      <nav className=" h-16 container-3 flex bg-blue-200 text-2xl">
        <div className="flex items-center flex-1">
          <div className="flex-1 flex justify-center">Start</div>
          <div className="ml-auto text-white">Logo</div>
        </div>
      </nav>

      <header className="p-3 container-3 flex justify-center">
        <h1 className="text-3xl">Din bokning</h1>
      </header>
      {/*        <section className="flex container-3">
                <div className="bg-blue-200 flex flex-1 m-1 justify-center">
                    Bekräftade
                </div>
                <div className="flex flex-1 m-1 justify-center border-grey border-2 ">
                    Behandlas
                </div>
            </section>*/}
      <main>
        <div className="grid grid-rows-3 gap-4">
          <div>
            <h2 className="flex justify-center">{status}</h2>
          </div>
          <div>
            <BookingCard key={host?.hostId} host={host} status={status} />
          </div>
        </div>
      </main>
    </>
  );
}

export default MyReservations;
