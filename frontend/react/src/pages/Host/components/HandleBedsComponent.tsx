import * as React from "react";
import {useState} from "react";
import {putNrBeds} from "../../../api/PutNrBeds";



export default function HandleBedsComponent() {
    const [selectedBeds, setSelectedBeds] = useState(0);

    const hostId = "4"

    const handleSave = async () => {
        try {
            await putNrBeds(selectedBeds, hostId)
        } catch (err) {
            console.log(err)
        }
    };

    return (
        <>
            <h2 className="text-2xl font-bold ">Antal lediga sängar:</h2>
            <div className="mt-2">
                <select
                    value={selectedBeds}
                    onChange={(e) => setSelectedBeds(Number(e.target.value))}
                    className="p-2 rounded border border-gray-300"
                >
                    {[1, 2, 3, 4, 5].map((beds) => (
                        <option key={beds} value={beds}>
                            {beds}
                        </option>
                    ))}
                </select>
            </div>
            <button
                onClick={handleSave}
                className="bg-blue-500 text-white rounded p-2 mt-4"
            >
                Spara
            </button>
        </>)

}