export interface IVacanciesViewModel {
    vacancies: IVacancy[];
}

export interface IVacancy{
    id: string;
    host: IHost;
    bedId: string;
}

export interface IHost {
    id: string;
    name: string;
    address: IAddress;
    image: string;
}

export interface IAddress {
    id: string;
    street: string;
    streetNum: string;
    postalCode: string;
    cityName: string;
}
