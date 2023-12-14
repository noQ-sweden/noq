import React from 'react';

interface Button2Props {
  title: string
  onClick: () => void;
  isLoading: boolean
}

const Button1Error = (props: Button2Props) => {
  return (
      <button onClick={() => props.onClick()}
              className="btn bg-transparent border-red-500 w-36 hover:bg-transparent hover:border-red-500">
        <p className={`${props.isLoading ? "hidden" : "block text-red-600 text-sm"}`}>{props.title}</p>
        <span
            className={`${props.isLoading ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
      </button>
  );
};

export default Button1Error;
