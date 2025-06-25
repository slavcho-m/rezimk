import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface ITown {
  id: number,
  name: string
}

export interface ITownDto {
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class TownsService {
  towns = [] as ITown[];
  townsSubject = new Subject<ITown[]>();
  townsSubject$ = this.townsSubject.asObservable();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<ITown[]>('http://localhost:8080/api/towns')
      .subscribe(data => {
        this.towns = data;
        this.townsSubject.next(this.towns);
      })
  }

  addTown(townDto: ITownDto) {
    console.log('tr');
    this.http.post('http://localhost:8080/api/towns/add', townDto).subscribe();
  }
}
