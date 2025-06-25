import { Component, OnDestroy, OnInit } from '@angular/core';
import { ITown, ITownDto, TownsService } from '../../services/towns.service';
import { Subject, takeUntil } from 'rxjs';
import { AmenitiesService, IAmenity } from '../../services/amenities.service';
import { ApartmentsService, IApartment } from '../../services/apartments.service';
import { IRoom, RoomsService } from '../../services/rooms.service';
import { IReview, ReviewsService } from '../../services/reviews.service';
import { CommonModule } from '@angular/common';
import { AdvancedSearchbarComponent } from "../../components/advanced-searchbar/advanced-searchbar.component";

@Component({
  selector: 'app-home',
  imports: [CommonModule, AdvancedSearchbarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>;
  towns = [] as ITown[];
  amenities = [] as IAmenity[];
  apartments = [] as IApartment[];
  rooms = [] as IRoom[];
  reviews = [] as IReview[];

  constructor( 
    private townsService: TownsService,
    private amenitiesService: AmenitiesService,
    private apartmentsService: ApartmentsService,
    private roomsService: RoomsService,
    private reviewsService: ReviewsService
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
  onInput(value: any) : void {
    this.inputForTownValue = value.target.value;
  }

  onTownSubmit() {
    const townDto = {
      name: this.inputForTownValue
    } as ITownDto;

    this.townsService.addTown(townDto);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
