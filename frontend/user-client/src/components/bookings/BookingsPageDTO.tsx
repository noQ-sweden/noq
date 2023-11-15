export interface BookingsPageDTO {
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
  newValue: string,
  updateChangeType: UpdateChangeType
}

export interface FilterSearchReqBody {
  area: string
  sort: string
}

/*enums*/
export enum Status {
  APPROVED = "APPROVED",
  PENDING = "PENDING",
  RESERVED = "RESERVED",
  CANCELLED = "CANCELLED",
  DENIED = "DENIED"
}

export enum UpdateChangeType {
  UPDATE_STATUS = "UPDATE_STATUS"
}

/*Mock*/
export const bookingsPageDTOMock: BookingsPageDTO = {
  id: "",
  reservations: [{id: "", name: "", queuingPlace: 0, status: Status.APPROVED}]
}
