import { createContext, useState, ReactNode, useEffect } from "react";
import { IHostPageModel } from "../pages/host/IHostPageModel";
import { getHost } from "../api/HostPageApi";


const HostPageContext = createContext<IHostPageModel>({} as IHostPageModel);

interface ChildrenProp {
  children?: ReactNode;
}

const HostPageProvider = ({ children }: ChildrenProp) => {
  const [hostPage, setHostPage] = useState<IHostPageModel>({
    id: "",
    name: "",
  });
  const hostId = "host3";

  const fetchHostView = async () => {
    try {
      const response = await getHost(hostId);
      if (response?.data) {
        setHostPage(response.data);
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
  );
};

export { HostPageContext, HostPageProvider };
