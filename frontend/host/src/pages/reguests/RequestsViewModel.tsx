export interface RequestsViewModel {
  id: string
  requests: Request[];
}

export interface Request {
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
