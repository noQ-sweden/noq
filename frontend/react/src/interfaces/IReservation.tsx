
export interface IReservation {
    reservationId: string;
    host: IHost;
    user: IUser;
}


interface IHost {
    hostId: string;
    name: string;
    address: {
        id: string;
        street: string;
        streetNum: string;
        postalCode: string;
        cityName: string;
    };
    image: string;
    beds: number;
}


interface IUser{
    id: string
    name: string
    reservation: boolean
}