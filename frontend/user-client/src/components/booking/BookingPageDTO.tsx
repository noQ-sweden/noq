export interface BookingPageDTO {
  id: string
  hostId: string;
  name: string;
  address1: string;
  address2: string;
  postalCode: string;
  city: string;
  countOfAvailablePlaces: number;
  totalAvailablePlaces: number;
}

/*Mock*/
export const bookingPageMock: BookingPageDTO = {
  id: "686569fa-4ec0-4c66-9b98-9e9858fdcc1d",
  hostId: "686569fa-4ec0-4c66-9b98-9e9858fdcc8d",
  name: "Boställe 1",
  address1: "Stockholmsgatan 1",
  address2: "Södermalm",
  postalCode: "11826",
  city: "Stockholm",
  countOfAvailablePlaces: 5,
  totalAvailablePlaces: 10,
}


