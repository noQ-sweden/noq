"use client";
import Link from "next/link";
import { revalidateCache } from "@/app/actions";

interface SideMenuLargeScreenProps {
  setSelected: (value: string) => void;
  selected: string;
}

const SideMenuLargeScreen = (props: SideMenuLargeScreenProps) => {
  return (
    <div className="fixed inset-y-15 bg-zinc-50 left-0 h-full w-64 xxs:hidden md:block">
      <section className={""}>
        <ul className="bg-transparent">
          {/*<li>*/}
          {/*  <Link*/}
          {/*      href={"/my-locations"}*/}
          {/*      onClick={async () => {*/}
          {/*        props.setSelected("/my-locations")*/}
          {/*        await revalidateCache("my-locations")*/}
          {/*      }}*/}
          {/*      className={`*/}
          {/*      btn w-full border-none border-indigo-500 font-normal rounded-none no-animation text-emerald-700 justify-start*/}
          {/*      ${props.selected.includes("/my-locations") ? "bg-emerald-700 hover:bg-emerald-700 text-white" : "hover:bg-zinc-100 bg-zinc-50"}`}*/}
          {/*  >*/}
          {/*    Mina Boenden*/}
          {/*  </Link>*/}
          {/*</li>*/}
          <li>
            <Link
              href={"/requests"}
              onClick={async () => {
                props.setSelected("/requests");
                await revalidateCache("requests");
              }}
              className={`btn w-full border-none border-indigo-500 font-normal rounded-none no-animation justify-start
                    ${
                      props.selected.includes("/requests")
                        ? "bg-primary hover:bg-primary text-white"
                        : "hover:bg-zinc-100 bg-zinc-50 text-primary "
                    }`}
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
