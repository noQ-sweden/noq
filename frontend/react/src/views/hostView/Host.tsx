import {useEffect, useState} from "react";
import axios from "axios";

interface IRequest {
    id: string;
    name: string;
}

export default function Host() {
    const [selectedBeds, setSelectedBeds] = useState(0);
    const [requests, setRequests] = useState<IRequest[]>([]);
    const [approvedIds, setApprovedIds] = useState<string[]>([]);

    const hostId = "4"

    const getAllRequests = async () =>{
        try {
            //const response = await axios.get(`https://ca-noq-backend.thankfulglacier-35d24b26.swedencentral.azurecontainerapps.io/api/get-reservations/${hostId}`);
            const response = await axios.get(`http://localhost:8080/api/reservation/get-reservations/${hostId}`);
            console.log(response.data)
            setRequests(response.data)
        } catch (error) {
            console.error(error);
        }
    }

    useEffect(()=> {
        getAllRequests()
    }, [])

    const handleSave = () => {
        // put to backend w nr of beds
    };

    const handleApprove = () => {
     // put to backend with string ids
        //
    };

    const toggleCheckbox = (id: string) => {
        if (approvedIds.includes(id)) {
            setApprovedIds(approvedIds.filter((approvedId) => approvedId !== id));
        } else {
            setApprovedIds([...approvedIds, id]);
        }
    };

    return (
        <div className="flex items-center justify-center">
            <div className="p-4">
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

                <h2 className="text-2xl font-bold mt-8">Förfrågningar:</h2>
                <div className="mt-2">
                    {requests.map((request) => (
                        <div
                            key={request.id}
                            className="flex items-center space-x-2 mt-2"
                        >
                            <input
                                type="checkbox"
                                checked={approvedIds.includes(request.id)}
                                onChange={() => toggleCheckbox(request.id)}
                            />
                            <span>{request.name}</span>
                        </div>
                    ))}
                </div>
                <button
                    onClick={handleApprove}
                    className="bg-green-500 text-white rounded p-2 mt-4"
                >
                    Godkänn
                </button>
            </div>
        </div>
    );
}