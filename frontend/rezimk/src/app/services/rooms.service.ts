import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IApartment } from './apartments.service';
import { IAmenity } from './amenities.service';
import { Subject } from 'rxjs';

export enum ERoomType {
  SINGLE, DOUBLE, SUITE, STUDIO, FAMILY
}

export interface IRoom {
  id: number,
  roomType: ERoomType,
  capacity: number,
  pricePerNight: number,
  apartment: IApartment,
  amenities: IAmenity[],
}

@Injectable({
  providedIn: 'root'
})
export class RoomsService {
  rooms = [] as IRoom[]
  roomsSubject = new Subject<IRoom[]>();
  roomsSubject$ = this.roomsSubject.asObservable();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IRoom[]>('http://localhost:8080/api/rooms')
      .subscribe(data => {
        this.rooms = data;
        this.roomsSubject.next(this.rooms);
      })
  }
  
}
