export interface IReservationsViewModel{
    reservationId: string,
    host: IHost;
}

export interface IHost{
    hostName: string;
    hostImage: string;
    address: IAddress;
}

export interface IAddress {
    street: string;
    streetNum: string;
    postalCode: string;
    cityName: string;
}