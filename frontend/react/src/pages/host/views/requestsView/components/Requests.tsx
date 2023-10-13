import React, {useContext, useEffect, useState} from "react";
import {Typography} from "@material-tailwind/react";
import {RequestsViewContext, RequestsViewModelEmptyData} from "../RequestsViewModelContext";
import {RequestsViewModel} from "../RequestsViewModel";

export default function Requests() {
  const requestsViewModelContext = useContext<RequestsViewModel>(RequestsViewContext)

  const [requestsViewModel, setRequestsViewModel] = useState<RequestsViewModel>(RequestsViewModelEmptyData);
  // const [checkedIds, setCheckedIds] = useState<string[]>([]);

  useEffect(() => {
    setRequestsViewModel(requestsViewModelContext)
  }, [])

  // const toggleCheckbox = (id: string) => {
  //   if (checkedIds.includes(id)) {
  //     setCheckedIds(checkedIds.filter((checkedIds) => checkedIds !== id));
  //   } else {
  //     setCheckedIds([...checkedIds, id]);
  //   }
  // };
  const handleApprove = () => {
    console.log("handleApprove")
  };
  const handleReject = () => {
    console.log("handleReject")
  };

  console.log(requestsViewModel)

  return (
      <main>
        <div className="container px-8 py-4 ">
          <Typography variant="h2" color="blue-gray" className="py-2 flex justify-end">{"name"}</Typography>
          <div className="px-8 py-4 ">

            <Typography variant="h4" color="blue-gray" className="border-b-2 py-2">
              Hantera förfrågningar
            </Typography>
          </div>

          <div className="flex justify-start px-4  laptop:justify-self-start laptop:self-end">
            <div className="p-5 max-w-sm flex flex-col gap-y-4 border-b-2">
              <Typography>Förfrågningar:</Typography>
              <div className="mt-2 min-w-xs">
                {requestsViewModel.requests.map(request => (
                    <div
                        key={request.id}
                        className="flex items-center space-x-2 divide-y divide-lilac bg-gray mb-2"
                    >
                      <Typography>
                        {request.name}
                      </Typography>

                      {/*<input*/}
                      {/*    type="checkbox"*/}
                      {/*    checked={checkedIds.includes(request.reservationId)}*/}
                      {/*    onChange={() => toggleCheckbox(request.reservationId)}*/}
                      {/*/>*/}
                    </div>
                ))}
              </div>
              <div className="flex gap-5 justify-self-end">
                <button
                    onClick={handleApprove}
                    className="bg-green text-white rounded px-5 py-2 mt-4"
                >
                  Godkänn
                </button>
                <button
                    onClick={handleReject}
                    className="bg-amber-500 text-white rounded p-2 mt-4"
                >
                  Neka
                </button>
              </div>
            </div>

          </div>
          {/*<div className="ml-4 my-2 w-64">*/}
          {/*  <div className="p-5 max-w-sm border-b-2">*/}
          {/*    <Typography>Godkända:</Typography>*/}
          {/*    <div className="mt-2">*/}
          {/*      {requestsViewModel?.requests.filter(request => request.status === "RESERVED")*/}
          {/*          .map((request) => (*/}
          {/*              <div*/}
          {/*                  key={request.reservationId}*/}
          {/*                  className="flex items-center space-x-2 mt-2 divide-y"*/}
          {/*              >*/}
          {/*                {request.client.name}*/}
          {/*              </div>*/}
          {/*          ))}*/}
          {/*    </div>*/}
          {/*  </div>*/}

          {/*  <div className="p-5 max-w-sm border-b-2">*/}
          {/*    <Typography>Nekade:</Typography>*/}
          {/*    <div className="mt-2">*/}
          {/*      {requestsViewModel?.requests.filter(request => request.status === "CANCELLED")*/}
          {/*          .map((request) => (*/}
          {/*              <div*/}
          {/*                  key={request.reservationId}*/}
          {/*                  className="flex items-center space-x-2 mt-2 divide-y bg-gray"*/}
          {/*              >*/}
          {/*                {request.client.name}*/}
          {/*              </div>*/}
          {/*          ))}*/}
          {/*    </div>*/}
          {/*  </div>*/}

          {/*</div>*/}
        </div>
      </main>
  );
}
