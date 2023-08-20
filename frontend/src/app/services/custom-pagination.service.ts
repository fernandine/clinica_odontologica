import { Injectable } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material/paginator';

@Injectable({
  providedIn: 'root'
})
export class CustomPaginationService extends MatPaginatorIntl {

  override itemsPerPageLabel = 'Itens por página';

  override getRangeLabel = (page: number, pageSize: number, length: number) => {
    const start = page * pageSize + 1;
    const end = (page + 1) * pageSize > length ? length : (page + 1) * pageSize;
    return `${start} - ${end} de ${length}`;
  }
}
