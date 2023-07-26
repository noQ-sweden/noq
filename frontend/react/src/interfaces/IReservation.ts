
export interface IReservation {
    reservationId: string;
    host: IHost;
    user: IUser;
}


export interface IHost {
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


export interface IUser{
    id: string
    name: string
    reservation: boolean
}