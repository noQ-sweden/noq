export interface RequestsViewModel {
  id: string
  reservations: Reservation[];
}

export interface Reservation {
  id: string;
  name: string
  queuingPlace: number
  status: Status
}

export enum Status {
  APPROVED = "APPROVED",
  PENDING = "PENDING",
  RESERVED = "RESERVED",
  DENIED = "DENIED",
  CANCELLED = "CANCELLED"
}
