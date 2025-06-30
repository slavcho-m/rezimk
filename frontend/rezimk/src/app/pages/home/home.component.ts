import { Component, OnDestroy, OnInit } from '@angular/core';
import { TownsService } from '../../services/towns.service';
import { Subject, takeUntil } from 'rxjs';
import { AmenitiesService } from '../../services/amenities.service';
import { ApartmentsService } from '../../services/apartments.service';
import { RoomsService } from '../../services/rooms.service';
import { ReviewsService } from '../../services/reviews.service';
import { CommonModule } from '@angular/common';
import { AdvancedSearchbarComponent } from "../../components/advanced-searchbar/advanced-searchbar.component";
import { ITown, IAmenity, IApartment, IRoom, IReview, ITownDto, ERating } from '../../utils/project.utils';
import { Router, ActivatedRoute } from '@angular/router';
import { WeatherCardComponent } from "../../components/weather-card/weather-card.component";

const imagesArr = [
  'apartment-1.jpg',
  'apartment-2.jpeg',
  'apartment-3.jpg',
  'apartment-4.jpg',
  'apartment-5.jpg',
  'apartment-6.jpg',
  'apartment-7.jpeg',
  'apartment-8.jpg',
  'apartment-9.jpg',
  'apartment-10.webp'
]

@Component({
  selector: 'app-home',
  imports: [CommonModule, AdvancedSearchbarComponent, WeatherCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>;
  towns = [] as ITown[];
  amenities = [] as IAmenity[];
  apartments = [] as IApartment[];
  apartmentsCpy = [] as IApartment[];
  rooms = [] as IRoom[];
  reviews = [] as IReview[];
  apartmentImages = {} as Record<number, string>;

  constructor( 
    private townsService: TownsService,
    private amenitiesService: AmenitiesService,
    private apartmentsService: ApartmentsService,
    private roomsService: RoomsService,
    private reviewsService: ReviewsService,
    private router: Router, 
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.townsService.init();
    this.amenitiesService.init();
    this.apartmentsService.init();
    this.roomsService.init();
    this.reviewsService.init();

    this.townsService.townsSubject$
      .pipe(takeUntil(this.destroy$))
      .subscribe((towns) => {
        this.towns = towns;
      })

    this.amenitiesService.amenitiesSubject$
      .pipe(takeUntil(this.destroy$))
      .subscribe((amenities) => {
        this.amenities = amenities;
      })

    this.apartmentsService.apartmentsSubject$
      .pipe(takeUntil(this.destroy$))
      .subscribe((apartments) => {
        this.apartments = apartments;
        this.apartmentsCpy = this.apartments;

        this.apartmentImages = {};
        this.apartmentsService.apartmentImages = {};
        for (const apt of this.apartments) {
          const randomIndex = Math.floor(Math.random() * imagesArr.length);
          this.apartmentsService.apartmentImages[apt.id] = imagesArr[randomIndex];
          this.apartmentImages[apt.id] = imagesArr[randomIndex];
        }
      })

    this.roomsService.roomsSubject$
      .pipe(takeUntil(this.destroy$))
      .subscribe((rooms) => {
        this.rooms = rooms;
      })

    this.reviewsService.reviewsSubject$
      .pipe(takeUntil(this.destroy$))
      .subscribe((reviews) => {
        this.reviews = reviews;
      })
  }

  inputForTownValue = '';
  onInput(value: string) : void {
    this.inputForTownValue = value;
    this.apartmentsCpy = this.apartments.filter(apartment => apartment.town.name.toLowerCase().includes(this.inputForTownValue.toLowerCase()));
  }

  getAverageRating(apartmentId: number): number {
    const apartmentReviews = this.reviews.filter(r => r.apartment.id === apartmentId);
    if (apartmentReviews.length === 0) return 0;

    const total = apartmentReviews.reduce((acc, r) => acc + this.getNumericRating(r.rating.toString()), 0);
    return total / apartmentReviews.length;
  }

  getLowestPrice(apartmentId: number): number {
    const relatedRooms = this.rooms.filter(room => room.apartment.id === apartmentId);
    if (relatedRooms.length === 0) return 0;

    return Math.min(...relatedRooms.map(room => room.pricePerNight));
  }

  // Converts rating enum to a number (assumes ONE_STAR = 1, etc.)
  getNumericRating(rating: string): number {
    switch (rating) {
      case 'ONE_STAR': return 1;
      case 'TWO_STAR': return 2;
      case 'THREE_STAR': return 3;
      case 'FOUR_STAR': return 4;
      case 'FIVE_STAR': return 5;
      default: return 0;
    }
  }

  navigateToApartment(id: number) {
    this.router.navigate(['apartments/', id], { relativeTo: this.route });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
