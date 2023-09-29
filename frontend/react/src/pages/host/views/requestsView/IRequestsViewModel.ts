export interface IRequestsViewModel {
    reservations: IReservation[];
}

export interface IReservation {
    reservationId: string;
    client: IClient;
    status: Status;
}

export interface IClient {
    id: string
    name: string
}

export enum Status {
    PENDING = "PENDING",
    RESERVED = "RESERVED",
    CANCELLED = "CANCELLED"
}