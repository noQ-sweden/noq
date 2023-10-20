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

/*reqBody*/
export interface UpdateReservationStatusField {
  reservationId: string,
  updateChangeType: UpdateChangeType
}

/*enums*/
export enum Status {
  APPROVED = "APPROVED",
  PENDING = "PENDING",
  RESERVED = "RESERVED",
  DENIED = "DENIED",
  CANCELLED = "CANCELLED"
}

export enum UpdateChangeType {
  ACCEPT = "ACCEPT",
  DENY = "DENY"
}

