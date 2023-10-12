import {createContext, ReactNode, useEffect, useState} from "react";
import {RequestsViewModel, Status} from "./RequestsViewModel";
import {fetchRequestsViewModel} from "../../../../api/RequestsViewApi";

export const RequestsViewContext = createContext<RequestsViewModel>({} as RequestsViewModel);

const RequestsViewModelMockData: RequestsViewModel = {
  id: "1",
  requests: [
    {id: "1", name: "John1", queuingPlace: 1, status: Status.PENDING},
    {id: "2", name: "John2", queuingPlace: 2, status: Status.PENDING},
    {id: "3", name: "John3", queuingPlace: 3, status: Status.PENDING},
    {id: "4", name: "John4", queuingPlace: 4, status: Status.PENDING},
  ]
}

export const RequestsViewModelEmptyData: RequestsViewModel = {
  id: "",
  requests: [{id: "", name: "", queuingPlace: 0, status: Status.PENDING}]
}

interface ChildrenProp {
  children?: ReactNode;
  hostId: string;
}

export const RequestsViewProvider = ({children, hostId}: ChildrenProp) => {
  const [hostPage, setHostPage] = useState<RequestsViewModel>();

  useEffect(() => {
    fetchRequestsViewModel(hostId)
        .then(setHostPage)
        .catch(reason => {
          setHostPage(RequestsViewModelMockData)
          console.log(reason)
        })
  }, []);

  return (
      <RequestsViewContext.Provider value={hostPage ? hostPage : RequestsViewModelMockData}>
        {children}
      </RequestsViewContext.Provider>
  )
}

