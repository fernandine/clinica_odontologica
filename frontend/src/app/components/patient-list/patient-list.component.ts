import { Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { Page } from 'src/app/common/pagination';
import { Patient } from '../../common/patient';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.scss']
})
export class PatientListComponent {

  @Input() patients: Patient[] = [];
  @Output() add: EventEmitter<boolean> = new EventEmitter(false);
  @Output() details: EventEmitter<Patient> = new EventEmitter(false);
  @Output() edit: EventEmitter<Patient> = new EventEmitter(false);
  @Output() remove: EventEmitter<Patient> = new EventEmitter(false);

  @Input() paginationData: any = { pageIndex: 0, pageSize: 10, totalItems: 0 };

  readonly displayedColumns = [
    'name',
    'appointmentDate',
    'dentist',
    'description',
    'actions'
  ];

  constructor(
    private patientService: PatientService
    ) {}
  ngOnInit(): void {
    this.loadList();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['paginationData'] && !changes['paginationData'].firstChange) {
      this.loadList();
    }
  }

  loadList() {
    const { pageIndex, pageSize } = this.paginationData;

    this.patientService
      .list(pageSize, pageIndex, pageSize)
      .subscribe((page: Page<Patient>) => {
        console.log('Página de pacientes:', page);
        this.paginationData.totalItems = page.totalElements;
        this.patients = page.content;
      });
  }
    onDetails(record: Patient) {
      this.details.emit(record);
    }

    onAdd() {
      this.add.emit(true);
    }

    onEdit(record: Patient) {
      this.edit.emit(record);
    }

    onRemove(record: Patient) {
      this.remove.emit(record);
    }

}
