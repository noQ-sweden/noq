export interface IRequestsViewModel {
    reservations: IReservation[];
}

export interface IReservation {
    reservationId: string;
    user: IUser;
    Status: Status;
}

export interface IUser {
    id: string
    name: string
}

export enum Status {
    PENDING = "PENDING",
    RESERVED = "RESERVED",
    CANCELLED = "CANCELLED"
}