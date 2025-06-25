import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface IAmenity {
  id: number,
  name: string
}

@Injectable({
  providedIn: 'root'
})
export class AmenitiesService {
  amenities = [] as IAmenity[];
  amenitiesSubject = new Subject<IAmenity[]>();
  amenitiesSubject$ = this.amenitiesSubject.asObservable();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IAmenity[]>('http://localhost:8080/api/amenities')
      .subscribe(data => {
        this.amenities = data;
        this.amenitiesSubject.next(this.amenities);
      })
  }
  
}
