"use client"
import Link from "next/link";
import {useEffect, useState} from "react";

const SideMenuLargeScreen = () => {
  const [selected, setSelected] = useState<string>("");

  useEffect(() => {
    setSelected(window.location.pathname);
  }, []);

  return (
      <div
          className="fixed inset-y-15 bg-zinc-300 left-0 h-full w-64 xxs:hidden md:block">
        <section className={""}>

          <ul className="bg-transparent">
            <li>
              <Link
                  href={"bookings"}
                  onClick={() => setSelected("/bookings")}
                  className={`
                  btn w-full border-none rounded-none no-animation hover:bg-zinc-300 text-black justify-start
                  ${selected.includes("/bookings") ? "bg-zinc-400 hover:bg-zinc-400" : "bg-zinc-300"}`}
              >
                Bokningar
              </Link>
            </li>
            <li>
              <Link href={"requests"}
                    onClick={() => setSelected("/requests")}
                    className={`btn w-full border-none rounded-none no-animation hover:bg-zinc-300 text-black justify-start
                    ${selected.includes("/requests") ? "bg-zinc-400 hover:bg-zinc-400" : "bg-zinc-300"}`}
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
