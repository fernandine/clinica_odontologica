import { Address } from "./address";
import { Role } from "./role";

export interface User {
  id: number;
  name: string;
  email: string;
  appointmentDate: Date;
  dentist: string;
  description: string;
  password: string;
  phone: string;
  roles: Role[];
  addressList: Address[];
}
