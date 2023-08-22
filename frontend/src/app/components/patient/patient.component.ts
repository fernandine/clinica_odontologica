import { Component, Input, ViewChild } from '@angular/core';
import { MatDialog, } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { Page } from 'src/app/common/pagination';
import { ReportService } from 'src/app/services/report.service';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { Patient } from '../../common/patient';
import { PatientService } from '../../services/patient.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.scss']
})
export class PatientComponent {

  patient$: Observable<Page<Patient>> | null = null;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  code = '01';
  acao = 'v';

  paginationData = {
    pageIndex: 0,
    pageSize: 10,
    pageElements: 0
  };

  constructor(
    private patientService: PatientService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar,
    private report: ReportService,

  ) {}

  ngOnInit() {
    this.refresh();
  }

  onPDF() {
    if (this.patient$) {
      const code = '01';
      const acao = 'v';

      this.report.getPDF(code, acao).subscribe(
        (pdfBlob: Blob) => {
          const pdfUrl = URL.createObjectURL(pdfBlob);
          window.open(pdfUrl, '_blank');
        },
        (error) => {
          console.error('Error fetching PDF:', error);
        }
      );
    }
  }

  refresh() {
    const pageSize = 10;
    const pageIndex = 0;
    const pageElements = 0;
    this.patientService.list(pageSize, pageIndex, pageElements)
    .pipe(
      catchError(error => {
        this.onError('Erro ao carregar paciente.');
        return of({ content: [], totalPages: 0, totalElements: 0 });
      }),
      map(({ content, totalPages, totalElements }) => ({ content, totalPages, totalElements } as Page<Patient>))
    )
    .subscribe(page => {
      //console.log('Página de pacientes:', page);
      this.patient$ = of(page);
      this.paginationData.pageElements = page.totalElements;
    });
  }

onPaginatorChange(event: PageEvent): void {
  this.paginationData.pageIndex = event.pageIndex;
  this.paginationData.pageSize = event.pageSize;
  this.paginationData.pageElements = event.length;
  this.refresh();
}

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(patient: Patient) {
    this.router.navigate(['edit', patient.id], { relativeTo: this.route });
  }

  onRemove(patient: Patient) {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: 'Tem certeza que deseja remover o paciente?'
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result) {
        this.patientService.remove(patient.id).subscribe({
          next: () => {
            this.refresh();
            this.snackBar.open('Paciente removido com sucesso!', 'X', {
              duration: 5000,
              verticalPosition: 'top',
              horizontalPosition: 'center'
            });
          },
          error: () => this.onError('Erro ao tentar remover.')
        });
      }
    });
  }
}
