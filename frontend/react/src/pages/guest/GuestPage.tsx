import {SideBarComponenet} from "./components/SideBarComponent";
import {Outlet} from "react-router";
import {getGuest} from "../../api/GuestPageApi";
import {useEffect, useState} from "react";
import {IGuestPageModel} from "../guest/IGuestPageModel"


export default function GuestPage() {
    const [user, setUser] = useState<IGuestPageModel>({id: "", name: ""})
    const userId = "1"

    const fetchView = async () => {
        try {
            const response = await getGuest(userId);
            if (response?.data) {
                setUser(response.data);
                console.log(response.data);
              }
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        fetchView()
    }, [])

    return (
        <>
            <div className="mt-12 flex">
                <div className="flex-none">
                    <aside>
                        <SideBarComponenet name={user.name}></SideBarComponenet>
                    </aside>
                </div>
                <div className="grow">
                    <Outlet/>
                </div>
            </div>
        </>
    );
}
