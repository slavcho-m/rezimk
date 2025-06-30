import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { IReview, IReviewDto } from '../utils/project.utils';



@Injectable({
  providedIn: 'root'
})
export class ReviewsService implements OnDestroy{
  reviews = [] as IReview[];
  reviewsSubject = new Subject<IReview[]>();
  reviewsSubject$ = this.reviewsSubject.asObservable();

  private destroy$ = new Subject<void>();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IReview[]>('/api/reviews')
      .subscribe(data => {
        this.reviews = data;
        this.reviewsSubject.next(this.reviews);
      })
  }

  fetchReviewById(id: number) {
    return this.http.get(`/api/reviews/${id}`);
  }

  addReview(reviewDto: IReviewDto) {
    this.http.post<IReview>('/api/reviews/add', reviewDto)
      .subscribe((createdReview) => {
        this.reviews.push(createdReview);
        this.reviewsSubject.next([...this.reviews]);
      });
  }

  editReview(id: number, reviewDto: IReviewDto) {
    this.http.put<IReview>(`/api/reviews/edit/${id}`, reviewDto)
      .subscribe((updatedReview) => {
        const index = this.reviews.findIndex(r => r.id === id);
        if (index > -1) {
          this.reviews[index] = updatedReview;
          this.reviewsSubject.next([...this.reviews]);
        }
      });
  }

  deleteReview(id: number) {
    this.http.delete(`/api/reviews/delete/${id}`)
    .subscribe(() => {
      this.reviews = this.reviews.filter(review => review.id !== id);
      this.reviewsSubject.next([...this.reviews]);
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
