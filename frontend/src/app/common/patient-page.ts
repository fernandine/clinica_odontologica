import { Patient } from "./patient";

export interface PatientPage {

    patients: Patient[];
    totalElements: number;
    totalPages?: number;
  }

