import {Button, Input, Typography} from "@material-tailwind/react";
import React, {ChangeEvent, useContext, useState} from "react";
import {updateName} from "../../../../../api/SettingsViewApi";
import {HostPageContext} from "../../../../../context/HostPageContext";
import {getHost} from "../../../../../api/HostPageApi";
import {IHostPageModel} from "../../../IHostPageModel";

export default function UpdateNameComponent() {
    const [username, setUsername] = useState("");
    const [error, setError] = useState("");

    const {id, name} = useContext(HostPageContext)


    const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
    };

    const updateUserName = async () => {
        try {
            await updateName(id, username)
        } catch (err) {
            setError(err.message || "Couldn't update name");
        }
    };

    return <>
        <Typography className="mb-4">Uppdatera namn:</Typography>
        <Input
            size="md"
            label="Ändra namn"
            value={username}
            onChange={handleUsernameChange}
            crossOrigin={undefined}

        />
        {
            error && <Typography className="text-red">{error}</Typography>
        }
        <Button color="blue" onClick={updateUserName} fullWidth className="my-2">Spara</Button>
    </>
}

