export interface IReservationsViewModel {
    reservationId: string,
    hostName: string;
    hostImage: string;
    address: IAddress
}

export interface IAddress {
    id: string;
    street: string;
    streetNum: string;
    postalCode: string;
    cityName: string;
}