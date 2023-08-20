import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { PatientComponent } from './patient/patient.component';
import { PatientFormComponent } from './patient-form/patient-form.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { MaterialModule } from '../material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { PatientRoutingModule } from './patient-routing.module';
import { AuthModule } from '../authentication/auth.module';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { CustomPaginationService } from '../services/custom-pagination.service';

@NgModule({
  declarations: [
    PatientComponent,
    PatientFormComponent,
    PatientListComponent,
  ],
  providers: [DatePipe, {provide: MatPaginatorIntl, useClass: CustomPaginationService}],
  imports: [
    CommonModule,
    PatientRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    AuthModule
  ]
})
export class PatientModule { }
