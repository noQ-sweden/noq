import {IHost} from "../interfaces/IHost";

export const Host: IHost[] = [
    {
        hostId: "c8116b2b-d509-41c5-ada6-4680f309293a",
        name: "Test-Härberget 1",
        address: {
            id: "70bc3278-9df5-47cd-a71b-70ae571582de",
            street: "Gatgatan",
            streetNum: "12",
            postalCode: "12345",
            cityName: "Stockholm",
        },
        image: "url/till/bild/pa/Harberget1.png",
        beds: 15,
    },
    {
        hostId: "d13d2b39-e23c-4e0e-bf81-db74a3b7c903",
        name: "Test-Härberget 2",
        address: {
            id: "57e5fc63-562c-4673-ab5e-846708a0f026",
            street: "Vägvägen",
            streetNum: "21",
            postalCode: "23546",
            cityName: "Lund",
        },
        image: "url/till/bild/pa/Harberget2.png",
        beds: 20,
    },
];