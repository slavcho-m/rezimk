import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApartmentsService } from '../../services/apartments.service';
import { RoomsService } from '../../services/rooms.service';
import { ReviewsService } from '../../services/reviews.service';
import { AmenitiesService } from '../../services/amenities.service';
import { IApartment, IRoom, IReview, IAmenity, ERating } from '../../utils/project.utils';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { AmenitySelectorDialogComponent } from '../../components/amenity-selector-dialog/amenity-selector-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';


@Component({
  selector: 'app-apartment',
  imports: [CommonModule, FormsModule, MatButtonModule, MatSelectModule, MatFormFieldModule, MatInputModule],
  templateUrl: './apartment.component.html',
  styleUrl: './apartment.component.css'
})
export class ApartmentComponent {
  apartment!: IApartment;
  apartmentImage = '';
  rooms: IRoom[] = [];
  reviews: IReview[] = [];
  allAmenities: IAmenity[] = [];

  selectedRating = 1;
  comment = '';

  constructor(
    private route: ActivatedRoute,
    private apartmentsService: ApartmentsService,
    private roomsService: RoomsService,
    private reviewsService: ReviewsService,
    private amenitiesService: AmenitiesService,
    private dialog: MatDialog,
  ) {}

  ngOnInit() {
    const apartmentId = Number(this.route.snapshot.paramMap.get('id'));

    this.roomsService.init();
    this.amenitiesService.init()
    this.reviewsService.init();

    this.apartmentsService.fetchApartmentById(apartmentId).subscribe(apartment => {
      this.apartment = apartment;
      this.apartmentImage = this.apartmentsService.apartmentImages[apartmentId];
    });

    this.roomsService.roomsSubject$.subscribe(rooms => {
      this.rooms = rooms.filter(room => room.apartment.id === apartmentId);
      console.log(this.rooms[0].reserved);
    });

    this.reviewsService.reviewsSubject$.subscribe(reviews => {
      this.reviews = reviews.filter(r => r.apartment.id === apartmentId);
    });

    this.amenitiesService.amenitiesSubject$.subscribe(amenities => {
      this.allAmenities = amenities;
    });
  }

  getRatingNumber(rating: ERating): number {
    switch(rating.toString()) {
      case "ONE_STAR":
        return 1
      case "TWO_STAR":
        return 2
      case "THREE_STAR":
        return 3
      case "FOUR_STAR":
        return 4
      case "FIVE_STAR":
        return 5
      default:
        return 1
    }
  }

  getRatingValueFromNumber(value: number): ERating {
    switch(value) {
      case 1:
        return ERating.ONE_STAR
      case 2:
        return ERating.TWO_STAR
      case 3:
        return ERating.THREE_STAR
      case 4:
        return ERating.FOUR_STAR
      case 5:
        return ERating.FIVE_STAR
      default:
        return ERating.ONE_STAR
    }
  }

  onAddReview() {
    const reviewDto = {
      apartmentId: this.apartment.id,
      comment: this.comment,
      rating: this.getRatingValueFromNumber(this.selectedRating)
    };

    this.reviewsService.addReview(reviewDto);
    console.log(reviewDto);
    this.comment = '';
    this.selectedRating = 1;
  }

  openAmenitySelector(room: IRoom) {
    const preselectedIds = room.amenities.map(a => a.id);

    const dialogRef = this.dialog.open(AmenitySelectorDialogComponent, {
      width: '450px',
      data: {
        amenities: this.allAmenities,
        preselectedIds
      }
    });

    dialogRef.afterClosed().subscribe((selectedIds: number[] | undefined) => {
      if (selectedIds && selectedIds.length) {
        console.log(selectedIds);
        this.roomsService.addAmenities(room.id, selectedIds);
      }
    });
  }

  getAmenityNames(room: IRoom): string {
    return room.amenities.map(a => a.name).join(', ');
  }

  handleRoomReservation(currentStatus: boolean, roomId: number) {
    if (currentStatus) {
      this.roomsService.freeRoom(roomId);
    } else {
      this.roomsService.reserveRoom(roomId);
    }
  }
}
