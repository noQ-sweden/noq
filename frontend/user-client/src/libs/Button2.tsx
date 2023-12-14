import React from 'react';

interface Button2Props {
  title: string
  onClick: () => void;
  isLoading: boolean
}

const Button2 = (props: Button2Props) => {
  return (
      <button onClick={() => props.onClick()}
              className="btn bg-primary w-42">
        <p className={`${props.isLoading ? "hidden" : "block text-white text-sm"}`}>{props.title}</p>
        <span
            className={`${props.isLoading ? "block" : "hidden"} loading loading-spinner loading-xs`}></span>
      </button>
  );
};

export default Button2;
