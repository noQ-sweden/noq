export interface RequestsPageDTO {
  id: string
  approvedBookings: Booking[];
  disapprovedBookings: Booking[];
  pendingBookings: Booking[];
}

export interface Booking {
  id: string;
  userId: string
  name: string
  unoCode: string
  queuingPlace: number
  status: Status
}

/*enums*/
export enum Status {
  APPROVED = "APPROVED",
  PENDING = "PENDING",
  DENIED = "DENIED"
}

/*reqBody*/
export interface UpdateReservationStatusField {
  reservationId: string,
  newValue: string,
  updateChangeType: UpdateChangeType
}

export interface BookingReqBody {
  hostId: string
  bookingId: string
}

export enum UpdateChangeType {
  UPDATE_STATUS = "UPDATE_STATUS"
}

/*Mock*/
export const requestsPageDTOMock: RequestsPageDTO = {
  id: "id",
  approvedBookings: [
    {id: "1", userId: "1", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.APPROVED},
    {id: "2", userId: "2", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.APPROVED},
  ],
  disapprovedBookings: [
    {id: "1", userId: "1", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.DENIED},
    {id: "2", userId: "2", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.DENIED},
  ],
  pendingBookings: [
    {id: "1", userId: "1", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.PENDING},
    {id: "2", userId: "2", name: "name", unoCode: "HG56k", queuingPlace: 1, status: Status.PENDING},
  ]
}
