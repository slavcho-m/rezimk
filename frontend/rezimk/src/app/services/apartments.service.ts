import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { IApartment, IApartmentDto } from '../utils/project.utils';

@Injectable({
  providedIn: 'root'
})
export class ApartmentsService implements OnDestroy {
  apartments = [] as IApartment[]
  apartmentsSubject = new Subject<IApartment[]>();
  apartmentsSubject$ = this.apartmentsSubject.asObservable();

  apartmentImages = {} as Record<number, string>;

  private destroy$ = new Subject<void>();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IApartment[]>('/api/apartments')
      .subscribe(data => {
        this.apartments = data;
        this.apartmentsSubject.next(this.apartments);
      })
  }

  fetchApartmentById(id: number) {
    return this.http.get<IApartment>(`/api/apartments/${id}`);
  }

  addApartment(apartmentDto: IApartmentDto) {
    this.http.post<IApartment>('/api/apartments/add', apartmentDto)
      .subscribe((createdApartment) => {
        this.apartments.push(createdApartment);
        this.apartmentsSubject.next([...this.apartments]);
      });
  }

  editApartment(id: number, apartmentDto: IApartmentDto) {
    this.http.put<IApartment>(`/api/apartments/edit/${id}`, apartmentDto)
      .subscribe((updatedApartment) => {
        const index = this.apartments.findIndex(r => r.id === id);
        if (index > -1) {
          this.apartments[index] = updatedApartment;
          this.apartmentsSubject.next([...this.apartments]);
        }
      });
  }

  deleteApartment(id: number) {
    this.http.delete(`/api/apartments/delete/${id}`)
    .subscribe(() => {
      this.apartments = this.apartments.filter(apartment => apartment.id !== id);
      this.apartmentsSubject.next([...this.apartments]);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
