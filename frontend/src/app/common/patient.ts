import { Address } from "./address";

export interface Patient {
  id: string;
  name: string;
  phone: string;
  appointmentDate: Date;
  dentist: string;
  description: string;
  addressList: Address[];
}

