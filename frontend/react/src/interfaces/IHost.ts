export interface IHost {
  hostId: string;
  name: string;
  address: {
    id: string;
    street: string;
    streetNum: string;
    postalCode: string;
    cityName: string;
  };
  image: string;
  beds: number;
}
