import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IApartment } from './apartments.service';
import { Subject } from 'rxjs';

export enum ERating {
  ONE_STAR, TWO_STAR, THREE_STAR, FOUR_STAR, FIVE_STAR
}

export interface IReview {
  id: number,
  rating: ERating,
  comment: string,
  apartment: IApartment
}

@Injectable({
  providedIn: 'root'
})
export class ReviewsService {
  reviews = [] as IReview[];
  reviewsSubject = new Subject<IReview[]>();
  reviewsSubject$ = this.reviewsSubject.asObservable();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IReview[]>('http://localhost:8080/api/reviews')
      .subscribe(data => {
        this.reviews = data;
        this.reviewsSubject.next(this.reviews);
      })
  }
  
}
