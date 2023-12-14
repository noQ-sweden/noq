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
                  href={"/available-hosts"}
                  onClick={async () => {
                    props.setSelected("/available-hosts")
                    await revalidateCache("available-hosts")
                  }}
                  className={`
                  btn w-full border-none border-indigo-500 font-normal rounded-none no-animation text-emerald-700 justify-start
                  ${props.selected.includes("/available-hosts") ? "bg-primary hover:bg-primary text-white" : "hover:bg-zinc-100 bg-zinc-50"}`}
              >
                Boka Boende
              </Link>
            </li>
            <li>
              <Link href={"/my-bookings"}
                    onClick={async () => {
                      props.setSelected("/my-bookings")
                      await revalidateCache("my-bookings")
                    }}
                    className={`btn w-full border-none border-indigo-500 font-normal rounded-none no-animation text-emerald-700 justify-start
                    ${props.selected.includes("/my-bookings") ? "bg-primary hover:bg-primary text-white" : "hover:bg-zinc-100 bg-zinc-50"}`}
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
