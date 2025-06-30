import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { ITown, ITownDto } from '../utils/project.utils';

@Injectable({
  providedIn: 'root'
})
export class TownsService implements OnDestroy {
  towns = [] as ITown[];
  townsSubject = new Subject<ITown[]>();
  townsSubject$ = this.townsSubject.asObservable();

  private destroy$ = new Subject<void>();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<ITown[]>('/api/towns')
      .subscribe(data => {
        this.towns = data;
        this.townsSubject.next(this.towns);
      })
  }

  addTown(townDto: ITownDto) {
    this.http.post<ITown>('/api/towns/add', townDto)
      .subscribe((createdTown) => {
        this.towns.push(createdTown);
        this.townsSubject.next([...this.towns]);
      });
  }

  editTown(id: number, townDto: ITownDto) {
    this.http.put<ITown>(`/api/towns/edit/${id}`, townDto)
      .subscribe((updatedTown) => {
        const index = this.towns.findIndex(r => r.id === id);
        if (index > -1) {
          this.towns[index] = updatedTown;
          this.townsSubject.next([...this.towns]);
        }
      });
  }

  deleteTown(id: number) {
    this.http.delete(`/api/towns/delete/${id}`)
    .subscribe(() => {
      this.towns = this.towns.filter(town => town.id !== id);
      this.townsSubject.next([...this.towns]);
    });
  }

  fetchTownById(id: number) {
    return this.http.get(`/api/towns/${id}`);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
