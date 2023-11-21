import React from 'react';

interface Button1Props {
  title: string
  onClick: () => void;
  isLoading: boolean
}

const Button1 = (props: Button1Props) => {
  return (
      <button onClick={() => props.onClick()}
              className="btn bg-emerald-700 w-36 hover:bg-emerald-700">
        <p className={`${props.isLoading ? "hidden" : "block text-white text-sm"}`}>{props.title}</p>
        <span
            className={`${props.isLoading ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
      </button>
  );
};

export default Button1;
