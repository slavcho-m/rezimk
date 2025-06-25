import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ITown } from './towns.service';
import { Subject } from 'rxjs';

export interface IApartment {
  id: number,
  name: string,
  address: string,
  description: string,
  town: ITown
}

@Injectable({
  providedIn: 'root'
})
export class ApartmentsService {

  apartments = [] as IApartment[]
  apartmentsSubject = new Subject<IApartment[]>();
  apartmentsSubject$ = this.apartmentsSubject.asObservable();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IApartment[]>('http://localhost:8080/api/apartments')
      .subscribe(data => {
        this.apartments = data;
        this.apartmentsSubject.next(this.apartments);
      })
  }
}
