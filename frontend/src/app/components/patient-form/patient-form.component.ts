import { Location} from '@angular/common';
import { Component } from '@angular/core';
import { FormGroup, NonNullableFormBuilder, UntypedFormArray, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Patient } from '../../common/patient';
import { PatientService } from '../../services/patient.service';
import { FormUtilsService } from '../../services/form-utils.service';
import { MatDialog } from '@angular/material/dialog';
import { Address } from '../../common/address';

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
    public formUtils: FormUtilsService,
    private dialog: MatDialog,
  ) {}

  ngOnInit(): void {
   const patient: Patient = this.route.snapshot.data['patient'];

    this.form = this.formBuilder.group({
      id: [patient.id],
      name: [patient.name, [Validators.minLength(5), Validators.maxLength(50)]],
      phone: [patient.phone, [Validators.required]],
      appointmentDate: [patient.appointmentDate || null, [Validators.required]],
      dentist: [patient.dentist, [Validators.minLength(5), Validators.maxLength(50)]],
      description: [patient.description, [Validators.minLength(5), Validators.maxLength(200)]],
      addressList: this.formBuilder.array(this.retrieveaddress(patient), Validators.required),
    });

  }

  private retrieveaddress(patient: Patient) {
    const addressList = [];
    if (patient?.addressList) {
      patient.addressList.forEach(address => addressList.push(this.createAddress(address)));
    } else {
      addressList.push(this.createAddress());
    }
    return addressList;
  }

  private createAddress(address: Address = {
    id: '',
    cep: '',
    logradouro: '',
    complemento: '',
    bairro: '',
    localidade: '',
    uf: '',

  }) {
    return this.formBuilder.group({
      id: [address.id],
      cep: [ address.cep, [Validators.required] ],
      logradouro: [ address.logradouro, [Validators.required] ],
      complemento: [ address.complemento,[Validators.required] ],
      bairro: [ address.bairro,[Validators.required] ],
      localidade: [ address.localidade,[Validators.required] ],
      uf: [ address.uf,[Validators.required] ],
    });
  }

  getAddressFormArray() {
    return (<UntypedFormArray>this.form.get('addressList')).controls;
  }

  getAddressErrorMessage(fieldName: string, index: number) {
    return this.formUtils.getFieldFormArrayErrorMessage(
      this.form,
      'adressList',
      fieldName,
      index
    );
  }
  addAddress(): void {
    const addressList = this.form.get('addressList') as UntypedFormArray;
    addressList.push(this.createAddress());
  }

  removeAddress(index: number) {
    const addressList = this.form.get('addressList') as UntypedFormArray;
    addressList.removeAt(index);
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
