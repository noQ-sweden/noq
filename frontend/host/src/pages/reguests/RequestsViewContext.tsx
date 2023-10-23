import React, {createContext, ReactNode} from 'react';
import {RequestsViewModel, Status} from "./RequestsViewModel";
import {useQuery} from "@tanstack/react-query";
import {FETCH_REQUESTS, fetchRequests} from "./RequestsViewAPI";
import FetchIsLoading from "../../components/FetchIsLoading";

export const RequestsViewContext = createContext<RequestsViewModel>({} as RequestsViewModel)

export const requestsViewModelEmptyData: RequestsViewModel = {
  id: "",
  reservations: [{id: "", name: "", queuingPlace: 0, status: Status.APPROVED}]
}

const requestsViewModelMock: RequestsViewModel = {
  id: "",
  reservations: [{id: "", name: "", queuingPlace: 0, status: Status.APPROVED}]
}

type Props = {
  children: ReactNode
}

const RequestsViewProvider = ({children}: Props) => {

  const {isLoading, error, data} = useQuery({
    queryKey: [FETCH_REQUESTS],
    queryFn: () => fetchRequests().catch(reason => console.log(reason))
  })

  if (isLoading) return <FetchIsLoading/>

  return (
      <RequestsViewContext.Provider value={data ? data : requestsViewModelMock}>
        {children}
      </RequestsViewContext.Provider>
  );
};

export default RequestsViewProvider;
