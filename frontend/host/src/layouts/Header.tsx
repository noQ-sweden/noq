import React from "react";
import dd from "../assets/profile-svgrepo-com.svg";
import {LinkToLandingView} from "../routes/MainRoutes";
import {useNavigate} from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();

  return (
      <div className="navbar bg-base-200">
        <div className="flex-none">
          <label htmlFor="my-drawer" className="btn btn-square btn-ghost drawer-button md:hidden">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                 className="inline-block w-5 h-5 stroke-current">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path>
            </svg>
          </label>
        </div>
        <div className="flex-1">
          <button onClick={() => navigate(LinkToLandingView())} className="btn btn-ghost normal-case text-xl">
            NoQ
          </button>
        </div>
        <div className="flex-none gap-2">
          <label tabIndex={0} className="avatar">
            <div className="w-10 rounded-full"><img src={dd} alt={""}/></div>
          </label>
        </div>
      </div>
  );
};


export default Header;
