"use client"
import React from 'react';
import {RequestsViewModel} from "@/components/requests/RequestsViewModel";

interface RequestsProps {
  data: RequestsViewModel
}

const Requests = (props: RequestsProps) => {
  console.log(props.data)
  return (
      <div>
        Requests
      </div>
  );
};

export default Requests;
