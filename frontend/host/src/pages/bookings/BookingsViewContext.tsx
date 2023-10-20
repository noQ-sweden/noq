import React, {createContext, ReactNode} from 'react';
import {BookingsViewModel} from "./BookingsViewModel";
import {fetchBookings} from "./BookingsViewAPI";
import FetchIsLoading from "../../components/FetchIsLoading";
import {useQuery} from "@tanstack/react-query";

export const BookingsViewContext = createContext<BookingsViewModel>({} as BookingsViewModel)

const bookingsViewModelMock: BookingsViewModel = {
  id: "",
}

type Props = {
  children: ReactNode
}

const BookingsViewProvider = ({children}: Props) => {

  const {isLoading, error, data} = useQuery({
    queryKey: ["FETCH_BOOKINGSs"],
    queryFn: () => fetchBookings().catch(reason => console.log(reason)),
  })

  if (isLoading) return <FetchIsLoading/>

  return (
      <BookingsViewContext.Provider value={data ? data : bookingsViewModelMock}>
        {children}
      </BookingsViewContext.Provider>
  );
};

export default BookingsViewProvider;
