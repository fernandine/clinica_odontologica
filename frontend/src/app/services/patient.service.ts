import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../common/pagination';
import { Patient } from '../common/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private patUrl = environment.apiURL + '/patients';

  private cache: Patient[] = [];

  constructor(private httpClient: HttpClient) { }

  list(pageSize: number, pageIndex: number, pageElements: number): Observable<Page<Patient>> {
    const params = new HttpParams()
      .set('size', pageSize.toString())
      .set('page', pageIndex.toString())
      .set('items', pageElements.toString());

    return this.httpClient.get<Page<Patient>>(this.patUrl, { params });
  }

  loadById(id: string) {
    if (this.cache.length > 0) {
      const record = this.cache.find(cache => `${cache.id}` === `${id}`);
      return record != null ? of(record) : this.getById(id);
    }
    return this.getById(id);
  }

  getById(id: string) {
    return this.httpClient.get<Patient>(`${this.patUrl}/${id}`).pipe(first());
  }

  save(record: Partial<Patient>) {
    if (record.id) {
      return this.update(record);
    }
    return this.create(record);
  }

  create(record: Partial<Patient>) {
    return this.httpClient.post<Patient>(this.patUrl, record).pipe(first());
  }

  update(record: Partial<Patient>) {
    return this.httpClient.put<Patient>(`${this.patUrl}/${record.id}`, record).pipe(first());
  }


  remove(id: string) {
    return this.httpClient.delete(`${this.patUrl}/${id}`).pipe(first());
  }
/*
  markAsReceived(protocolNumber: string): Observable<Protocol> {
    return this.httpClient.get<Protocol>(`${this.protUrl}/searchBy/${protocolNumber}`).pipe(
      switchMap(protocol => {
        if (protocol) {
          return this.update({...protocol, receivedDate: new Date(), received: true});
        } else {
          throw new Error('Protocolo não encontrado!');
        }
      })
    );*/
  }
