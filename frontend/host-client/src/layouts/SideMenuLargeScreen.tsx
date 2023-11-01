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
          className="fixed inset-y-15 bg-zinc-300 left-0 h-full w-64 xxs:hidden md:block">
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
                  btn w-full border-none rounded-none no-animation hover:bg-zinc-300 text-black justify-start
                  ${props.selected.includes("/bookings") ? "bg-zinc-400 hover:bg-zinc-400" : "bg-zinc-300"}`}
              >
                Bokningar
              </Link>
            </li>
            <li>
              <Link href={"/requests"}
                    onClick={async () => {
                      props.setSelected("/requests")
                      await revalidateCache("requests")
                    }}
                    className={`btn w-full border-none rounded-none no-animation hover:bg-zinc-300 text-black justify-start
                    ${props.selected.includes("/requests") ? "bg-zinc-400 hover:bg-zinc-400" : "bg-zinc-300"}`}
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
