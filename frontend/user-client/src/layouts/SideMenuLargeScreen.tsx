"use client"
import Link from "next/link";
import {revalidateCache} from "@/app/actions";

interface SideMenuLargeScreenProps {
  setSelected: (value: string) => void;
  selected: string
}

const SideMenuLargeScreen = (props: SideMenuLargeScreenProps) => {
  return (
      <div
          className="fixed inset-y-15 bg-zinc-50 left-0 h-full w-64 xxs:hidden md:block">
        <section className={""}>

          <ul className="bg-transparent">
            <li>
              <Link
                  href={"/bookings"}
                  onClick={async () => {
                    props.setSelected("/bookings")
                    await revalidateCache("bookings")
                  }}
                  className={`
                  btn w-full border-none rounded-none font-normal no-animation hover:bg-zinc-50 text-emerald-700 justify-start
                  ${props.selected.includes("/bookings") ? "bg-emerald-700 hover:bg-emerald-800 text-white" : "hover:bg-zinc-100 bg-zinc-50"}`}
              >
                Boka Boende
              </Link>
            </li>
            <li>
              <Link href={"/requests"}
                    onClick={async () => {
                      props.setSelected("/requests")
                      await revalidateCache("requests")
                    }}
                    className={`btn w-full border-none border-indigo-500 font-normal rounded-none no-animation text-emerald-700 justify-start
                    ${props.selected.includes("/requests") ? "bg-emerald-700 hover:bg-emerald-800 text-white" : "hover:bg-zinc-100 bg-zinc-50"}`}
              >
                Mina Bokningar
              </Link>
            </li>
          </ul>
        </section>
      </div>
  );
};

export default SideMenuLargeScreen;