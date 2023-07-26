import Host from "./components/Host";
import {useEffect, useState} from "react";


export default function HostView () {
    const [hostId, setHostId] = useState("")

    useEffect(() => {
        setHostId("4")
    })

    return (
        <>
            <Host></Host>
        </>
    )
}