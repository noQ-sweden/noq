export interface MyLocationsDTO {
  id: string
  locations: Location[];
}

export interface Location {
  id: string;
  name: string
  address1: string
  address2: string
  countOfAvailablePlaces: number;
  totalAvailablePlaces: number;
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
export const bookingsPageDTOMock: MyLocationsDTO = {
  id: "1",
  locations: [
      {id: "1", name: "name", address1: "address1", address2: "address2", countOfAvailablePlaces: 1, totalAvailablePlaces: 5},
      {id: "2", name: "name", address1: "address1", address2: "address2", countOfAvailablePlaces: 2, totalAvailablePlaces: 5},
  ]
}
