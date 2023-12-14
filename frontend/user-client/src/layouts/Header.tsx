"use client"

import Image from 'next/image'; 
import Link from "next/link";

interface HeaderProps {
  setSelected:  (value: string) => void;
}

const Header = (props: HeaderProps) => {

  return (
      <div className="navbar bg-zinc-50">
        <div className="flex-none">
          <label htmlFor="my-drawer" className="btn btn-square btn-ghost drawer-button md:hidden">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 className="inline-block w-5 h-5 stroke-current">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path>
            </svg>
          </label>
        </div>
        <div className="flex-1">
          <Link href={"/"} onClick={() => props.setSelected("")} className="btn btn-ghost normal-case text-xl">
          <Image 
              src="/logo.png"
              alt="Logo" 
              width={50}
              height={50} 
            />
          </Link>
        </div>
        <div className="flex-none gap-2">
          <label tabIndex={0} className="avatar">
            {/*<div className="w-10 rounded-full"><Image src={dd} alt={""}/></div>*/}
          </label>
        </div>
      </div>
  );
};


export default Header;
