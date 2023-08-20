import { NgModule } from '@angular/core';
import { PatientComponent } from './patient/patient.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { RouterModule, Routes } from '@angular/router';
import { PatientResolver } from '../core/guards/patient.resolver';

const routes: Routes = [
  { path: '', component: PatientComponent },
  { path: 'new', component: PatientFormComponent, resolve: { patient: PatientResolver } },
  { path: 'edit/:id', component: PatientFormComponent, resolve: { patient: PatientResolver } }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientRoutingModule { }
