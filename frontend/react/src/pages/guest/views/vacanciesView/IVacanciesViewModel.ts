export interface IVacanciesViewModel {
    id: string,
    hostId: string,
    hostName: string,
    address: IAddress,
    hostImg: string,
    bedId: string
}


export interface IAddress {
    street: string;
    streetNum: string;
    postalCode: string;
    cityName: string;
}