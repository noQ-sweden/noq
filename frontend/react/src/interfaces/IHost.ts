export interface IHost {
  hostId: string;
  name: string;
  address: {
    street: string;
    streetNum: string;
    postalCode: number;
    cityName: string;
  };
  image: string;
  beds: number;
}
