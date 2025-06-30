import { ChangeDetectionStrategy, Component, Inject, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AmenitiesService } from '../../services/amenities.service';
import { ApartmentsService } from '../../services/apartments.service';
import { ReviewsService } from '../../services/reviews.service';
import { RoomsService } from '../../services/rooms.service';
import { TownsService } from '../../services/towns.service';
import { ModelType } from '../../utils/project.utils';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dialog',
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.css'
})
export class DialogComponent {
  readonly dialogRef = inject(MatDialogRef<DialogComponent>);
  private townsService = inject(TownsService);
  private apartmentsService = inject(ApartmentsService);
  private roomsService = inject(RoomsService);
  private reviewsService = inject(ReviewsService);
  private amenitiesService = inject(AmenitiesService);

  constructor(@Inject(MAT_DIALOG_DATA) public data: { modelType: ModelType, modelName: string, id: number }) {}

  onSubmit() {
    switch(this.data.modelType) {
      case 'town':
        this.townsService.deleteTown(this.data.id);
        break;
      case 'amenity':
        this.amenitiesService.deleteAmenity(this.data.id);
        break;
      case 'apartment':
        this.apartmentsService.deleteApartment(this.data.id);
        break;
      case 'review':
        this.reviewsService.deleteReview(this.data.id);
        break;
      case 'room':
        this.roomsService.deleteRoom(this.data.id);
        break;
    }

    this.dialogRef.close();
  }
}
