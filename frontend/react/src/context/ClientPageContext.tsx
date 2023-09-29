import { createContext, useState, ReactNode, useEffect } from "react";
import { getClient } from "../api/ClientPageApi";
import { IClientPageModel } from "../pages/client/IClientPageModel";

const ClientPageContext = createContext<IClientPageModel>({} as IClientPageModel);

interface ChildrenProp {
  children?: ReactNode;
}

const ClientPageProvider = ({ children }: ChildrenProp) => {
  const [clientPage, setClientPage] = useState<IClientPageModel>({
    id: "",
    name: "",
  });
  const clientId = "1";
  const fetchClientView = async () => {
    try {
      const response = await getClient(clientId);
      if (response?.data) {
        setClientPage(response.data);
        console.log(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchClientView();
  }, []);

  return (
    <ClientPageContext.Provider value={clientPage}>
      {children}
    </ClientPageContext.Provider>
  );
};

export { ClientPageContext, ClientPageProvider };
