import Link from "next/link";
import {useState} from "react";

const SideMenuLargeScreen = () => {
  const [selected, setSelected] = useState<string>(window.location.pathname);

  console.log(selected)
  return (
      <div
          className="fixed inset-y-15 bg-blue-100 left-0 h-full w-64 xxs:hidden md:block">
        <section className={""}>

          <ul className="bg-transparent">
            <li>
              <Link
                  href={"bookings"}
                  onClick={() => setSelected("/bookings")}
                  className={`
                  btn w-full border-none rounded-none no-animation hover:bg-zinc-200 text-black
                  ${selected.includes("/bookings") ? "bg-blue-800 hover:bg-blue-800" : "bg-transparent"}`}
              >
                Bokningar
              </Link>
            </li>
            <li>
              <Link href={"requests"}
                    onClick={() => setSelected("/requests")}
                    className={`btn w-full border-none rounded-none no-animation hover:bg-zinc-200 text-black text-normal
                    
                    ${selected.includes("/requests") ? "bg-blue-800 hover:bg-blue-800" : "bg-transparent"}`}
              >
                Förfrågningar
              </Link>
            </li>
          </ul>
        </section>
      </div>
  );
};

export default SideMenuLargeScreen;
