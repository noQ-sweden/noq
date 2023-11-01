"use client"
import React, {useEffect, useState} from 'react';
import {RequestsPageDTO} from "@/components/requests/RequestsPageDTO";
import RequestItem from "@/components/requests/components/RequestItem";

interface RequestsProps {
  data: RequestsPageDTO
}

const Requests = (props: RequestsProps) => {
  const [requestsViewModel, setRequestsViewModel] = useState<RequestsPageDTO>(props.data);

  useEffect(() => {
    setRequestsViewModel(props.data)
  }, []);

  return (
      <div>
        <main className={"flex justify-center"}>
          <div className={"flex flex-col w-11/12"}>
            <p className={"text-xl font-bold"}>Förfrågningar</p>
            <div className={"flex flex-col gap-1"}>
              {requestsViewModel.reservations && requestsViewModel.reservations.map(request => {
                return (
                    <div key={request.id}>
                      <RequestItem request={request}
                                   requestsViewModel={requestsViewModel}
                                   setRequestsViewModel={setRequestsViewModel}
                      />
                    </div>
                )
              })}
            </div>
          </div>
        </main>
      </div>
  );
};

export default Requests;
