import { createContext, useState, ReactNode, useEffect } from "react";
import { getGuest } from "../api/GuestPageApi";
import { IGuestPageModel } from "../pages/guest/IGuestPageModel";


const GuestPageContext = createContext<IGuestPageModel>({} as IGuestPageModel)

interface ChildrenProp {
children?: ReactNode
}

const GuestPageProvider = ({children}: ChildrenProp) => {
const [guestPage, setGuestPage] = useState<IGuestPageModel>({ id: "", name: "" });
const userId = "1"
const fetchGuestView = async () => {
    try {
      const response = await getGuest(userId);
      if (response?.data) {
        setGuestPage(response.data);
        console.log(response.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchGuestView();
  }, []);


return (
    <GuestPageContext.Provider value={guestPage}>
        {children}
    </GuestPageContext.Provider>
)
}

export {GuestPageContext, GuestPageProvider}