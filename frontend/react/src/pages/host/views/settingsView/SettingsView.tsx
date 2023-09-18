import * as React from "react";
import UpdateBedsComponent from "./components/UpdateBedsComponent";
import UpdatePhotoComponent from "./components/UpdatePhotoComponent";
import {Button, Input, Typography} from "@material-tailwind/react";

import UpdateNameComponent from "./components/UpdateNameComponent";
import {useContext} from "react";
import {HostPageContext} from "../../../../context/HostPageContext";

export default function SettingsView() {

    const {name} = useContext(HostPageContext)

    return (
        <main className="">
            <div className="container px-8 py-4 ">
                <Typography variant="h2" color="blue-gray" className="py-2 flex justify-end">{name}</Typography>
                <div className="px-8 py-4 ">

                    <Typography variant="h4" color="blue-gray" className="border-b-2 py-2">
                        Inställningar
                    </Typography>
                </div>
                <div
                    className="grid grid-flow-row px-4 py-4 my-2 gap-y-10 laptop:grid-flow-col laptop:grid-rows-2 laptop:grid-cols-auto laptop:gap-x-10">
                    <div className="flex justify-start px-4  laptop:justify-self-start laptop:self-end">
                        <UpdateBedsComponent></UpdateBedsComponent>
                    </div>
                    <div className="ml-4 my-2 w-64">
                        <UpdateNameComponent></UpdateNameComponent>
                    </div>
                    <UpdatePhotoComponent></UpdatePhotoComponent>
                </div>
            </div>
        </main>
    );
}
