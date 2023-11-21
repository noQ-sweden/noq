export interface BookingsPageDTO {
  availableHosts: Host[];
}

export interface Host {
  id: string;
  name: string;
  address1: string;
  address2: string;
  postalCode: string;
  city: string;
  countOfAvailablePlaces: number;
  totalAvailablePlaces: number;
}

/*reqBody*/
export interface UpdateReservationStatusField {
  hostId: string,
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
  availableHosts: [
    {
      id: "686569fa-4ec0-4c66-9b98-9e9858fdcc8d",
      name: "Boställe 1",
      address1: "Stockholmsgatan 1",
      address2: "Södermalm",
      postalCode: "11826",
      city: "Stockholm",
      countOfAvailablePlaces: 5,
      totalAvailablePlaces: 10,
    },
    {
      id: "979569aa-4ec0-8d66-9b98-9e9858fdcc9b",
      name: "Boställe 2",
      address1: "Stockholmsgatan 1",
      address2: "Södermalm",
      postalCode: "11826",
      city: "Stockholm",
      countOfAvailablePlaces: 5,
      totalAvailablePlaces: 10,
    },
    {
      id: "686569aa-4ec0-8d66-9b98-9e9858fdcc8a",
      name: "Boställe 3",
      address1: "Stockholmsgatan 1",
      address2: "Södermalm",
      postalCode: "11826",
      city: "Stockholm",
      countOfAvailablePlaces: 0,
      totalAvailablePlaces: 10,
    },
  ],
};

