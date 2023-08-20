import { DatePipe, Location} from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Patient } from '../../common/patient';
import { PatientService } from '../../services/patient.service';
import { FormUtilsService } from '../../services/form-utils.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent {

  form!: FormGroup;

  constructor(
    private formBuilder: NonNullableFormBuilder,
    private service: PatientService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute,
    private formUtils: FormUtilsService,
    private dialog: MatDialog,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
   const patient: Patient = this.route.snapshot.data['patient'];

    this.form = this.formBuilder.group({
      id: [''],
      name: [patient.name, [Validators.minLength(5), Validators.maxLength(50)]],
      appointmentDate: [patient.appointmentDate, [Validators.required]],
      dentist: [patient.dentist, [Validators.minLength(5), Validators.maxLength(50)]],
      description: [patient.description, [Validators.minLength(5), Validators.maxLength(200)]],
    });

  }

  onSubmit() {
    if (this.form.valid) {
      this.service.save(this.form.value as Patient).subscribe({
        next: () => this.onSuccess(),
        error: () => this.onError()
      });
    } else {
      this.formUtils.validateAllFormFields(this.form);
    }
  }

  onCancel() {
    this.location.back();
  }

  private onSuccess() {
    this.snackBar.open('Paciente salvo com sucesso!', '', { duration: 5000 });
    this.onCancel();
  }

  private onError() {
    this.dialog.open(ErrorDialogComponent, {
      data: 'Erro ao salvar paciente.'
    });
  }

  getErrorMessage(fieldName: string): string {
    return this.formUtils.getFieldErrorMessage(this.form, fieldName);
  }
}
