import { createContext, useState, ReactNode, useEffect } from "react";
import { IHostPageModel } from "../pages/host/IHostPageModel";
import mockHostPageData from "../pages/host/HostPageMockData.json"

const HostPageContext = createContext<IHostPageModel>({} as IHostPageModel)

interface ChildrenProp {
children?: ReactNode
}

const HostPageProvider = ({children}: ChildrenProp) => {
const [hostPage, setHostPage] = useState<IHostPageModel>({id: ""})

const fetchHostView = async () => {
    try {
   /* Implement actual fetch when backend has implemented an end-point */
const response = {data: mockHostPageData}

      if (response?.data) {
        setHostPage(response.data);
        console.log(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchHostView();
  }, []);

return (
    <HostPageContext.Provider value={hostPage}>
        {children}
    </HostPageContext.Provider>
)
}

export {HostPageContext, HostPageProvider}