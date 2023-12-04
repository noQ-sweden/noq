export interface MyBookingsPageDTO {
  id: string
  bookings: Booking[];
}

export interface Booking {
  id: string
  bookingStatus: string
  host: Host
}

export interface Host {
  id: string
  name: string
  address1: string
  address2: string
  postalCode: string
  city: string
  countOfAvailablePlaces: number
  totalAvailablePlaces: number
}

/*reqBody*/
export interface UpdateReservationStatusField {
  reservationId: string,
  newValue: string,
  updateChangeType: UpdateChangeType
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
export const requestsPageDTOMock: MyBookingsPageDTO = {
  id: "",
  bookings: []
}
