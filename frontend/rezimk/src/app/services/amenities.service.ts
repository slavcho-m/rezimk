import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { IAmenity, IAmenityDto } from '../utils/project.utils';

@Injectable({
  providedIn: 'root'
})
export class AmenitiesService implements OnDestroy {
  amenities = [] as IAmenity[];
  amenitiesSubject = new Subject<IAmenity[]>();
  amenitiesSubject$ = this.amenitiesSubject.asObservable();

  private destroy$ = new Subject<void>();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IAmenity[]>('http://localhost:8080/api/amenities')
      .subscribe(data => {
        this.amenities = data;
        this.amenitiesSubject.next(this.amenities);
      })
  }
  
  fetchAmenityById(id: number) {
    return this.http.get(`http://localhost:8080/api/amenities/${id}`);
  }

  addAmenity(amenityDto: IAmenityDto) {
    this.http.post<IAmenity>('http://localhost:8080/api/amenities/add', amenityDto)
      .subscribe((createdAmenity) => {
        this.amenities.push(createdAmenity);
        this.amenitiesSubject.next([...this.amenities]);
      });
  }

  editAmenity(id: number, amenityDto: IAmenityDto) {
    this.http.put<IAmenity>(`http://localhost:8080/api/amenities/edit/${id}`, amenityDto)
      .subscribe((updatedAmenities) => {
        const index = this.amenities.findIndex(r => r.id === id);
        if (index > -1) {
          this.amenities[index] = updatedAmenities;
          this.amenitiesSubject.next([...this.amenities]);
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
