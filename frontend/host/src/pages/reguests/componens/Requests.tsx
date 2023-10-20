import React, {useContext, useEffect, useState} from 'react';
import {RequestsViewContext, requestsViewModelEmptyData} from "../RequestsViewContext";
import {RequestsViewModel} from "../RequestsViewModel";

const Requests = () => {
  const requestsViewModelContext = useContext<RequestsViewModel>(RequestsViewContext);

  const [requestsViewModel, setRequestsViewModel] = useState<RequestsViewModel>(requestsViewModelEmptyData);

  useEffect(() => {
    setRequestsViewModel(requestsViewModelContext)
  }, []);

  const onDeleteBtn = () => {
    console.log("onDeleteBtn");
  };

  const onAcceptBtn = () => {
    console.log("onAcceptBtn");
  };

  return (
      <main className={"flex justify-center"}>
        <div className={"flex flex-col w-11/12"}>
          <p className={"text-xl font-bold"}>Förfrågningar</p>
          <div className={"flex flex-col gap-1"}>
            {requestsViewModel.reservations.map(request => {
              return (
                  <div className={"border-2 p-1"}>
                    <p>Namn: {request.name}</p>
                    <p>Status: {request.status}</p>
                    <div className={"flex gap-1"}>
                      <button onClick={() => onAcceptBtn()} className="btn btn-outline btn-success btn-xs">Acceptera</button>
                      <button onClick={() => onDeleteBtn()} className="btn btn-outline btn-error btn-xs">Neka</button>
                    </div>
                  </div>
              )
            })}
          </div>
        </div>
      </main>
  );
};

export default Requests;
