import { ChangeDetectionStrategy, Component, Inject, inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DialogComponent } from '../dialog/dialog.component';
import { AmenitiesService } from '../../services/amenities.service';
import { ApartmentsService } from '../../services/apartments.service';
import { ReviewsService } from '../../services/reviews.service';
import { RoomsService } from '../../services/rooms.service';
import { TownsService } from '../../services/towns.service';
import { ModelDtoLayouts, ModelType } from '../../utils/project.utils';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CamelToTitlePipe } from '../../utils/camel-to-title.pipe';

@Component({
  selector: 'app-form-dialog',
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, CommonModule, FormsModule, CamelToTitlePipe],
  templateUrl: './form-dialog.component.html',
  styleUrl: './form-dialog.component.css'
})
export class FormDialogComponent implements OnInit {
  readonly dialogRef = inject(MatDialogRef<DialogComponent>);
  private townsService = inject(TownsService);
  private apartmentsService = inject(ApartmentsService);
  private roomsService = inject(RoomsService);
  private reviewsService = inject(ReviewsService);
  private amenitiesService = inject(AmenitiesService);

  constructor(@Inject(MAT_DIALOG_DATA) public data: { modelType: ModelType, id?: number }) {}

  layout: string[] = [];
  formData: any = {};

  ngOnInit(): void {
    this.layout = ModelDtoLayouts[this.data.modelType];

    if (this.data.id !== undefined) {
      this.fetchItemById(this.data.modelType, this.data.id);
    }
  }

  fetchItemById(modelType: ModelType, id: number) {
    switch (modelType) {
      case 'town':
        this.townsService.fetchTownById(id).subscribe(data => this.formData = data);
        break;
      case 'apartment':
        this.apartmentsService.fetchApartmentById(id).subscribe(data => this.formData = data);
        break;
      case 'room':
        this.roomsService.fetchRoomById(id).subscribe(data => this.formData = data);
        break;
      case 'review':
        this.reviewsService.fetchReviewById(id).subscribe(data => this.formData = data);
        break;
      case 'amenity':
        this.amenitiesService.fetchAmenityById(id).subscribe(data => this.formData = data);
        break;
    }
  }

  onSubmit() {
    if (this.data.id !== undefined) {
      // Editing
      switch (this.data.modelType) {
        case 'room':
          this.roomsService.editRoom(this.data.id, this.formData);
          break;
        case 'town':
          this.townsService.editTown(this.data.id, this.formData);
          break;
        case 'amenity':
          this.amenitiesService.editAmenity(this.data.id, this.formData);
          break;
        case 'apartment':
          this.apartmentsService.editApartment(this.data.id, this.formData);
          break;
        case 'review':
          this.reviewsService.editReview(this.data.id, this.formData);
          break;
      }
    } else {
      // Adding
      switch (this.data.modelType) {
        case 'room':
          this.roomsService.addRoom(this.formData);
          break;
        case 'amenity':
          this.amenitiesService.addAmenity(this.formData);
          break;
        case 'apartment':
          this.apartmentsService.addApartment(this.formData);
          break;
        case 'review':
          this.reviewsService.addReview(this.formData);
          break;
        case 'town':
          this.townsService.addTown(this.formData);
          break;
      }
    }

    this.dialogRef.close();
  }
}
