import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Patient } from '../../common/patient';
import { PatientService } from '../../services/patient.service';

@Injectable({
  providedIn: 'root'
})
export class PatientResolver {

  constructor(private service: PatientService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Patient> {

    if (route.params && route.params['id']) {
      return this.service.loadById(route.params['id']);
    }

    return of ({
      id: '',
      name: '',
      appointmentDate: new Date(),
      dentist: '',
      description: ''
      }
    )};
  }



