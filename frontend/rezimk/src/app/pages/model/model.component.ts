import { ChangeDetectionStrategy, Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AmenitiesService } from '../../services/amenities.service';
import { ApartmentsService } from '../../services/apartments.service';
import { ReviewsService } from '../../services/reviews.service';
import { RoomsService } from '../../services/rooms.service';
import { TownsService } from '../../services/towns.service';
import { Subject, takeUntil } from 'rxjs';
import { ITown, IAmenity, IApartment, IRoom, IReview, ModelLayouts, ModelType } from '../../utils/project.utils';
import { ModelTableComponent } from "../../components/model-table/model-table.component";
import { CommonModule } from '@angular/common';
import { MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { DialogComponent } from '../../components/dialog/dialog.component';
import { FormDialogComponent } from '../../components/form-dialog/form-dialog.component';

@Component({
  selector: 'app-model',
  imports: [
    ModelTableComponent, 
    CommonModule,
  ],
  templateUrl: './model.component.html',
  styleUrl: './model.component.css',
})
export class ModelComponent implements OnInit {
  modelType!: ModelType;
  modelLayout!: string[];
  modelData!: any[];
  readonly dialog = inject(MatDialog);

  private destroy$ = new Subject<void>;

  constructor(
    private route: ActivatedRoute,
    private townsService: TownsService,
    private amenitiesService: AmenitiesService,
    private apartmentsService: ApartmentsService,
    private roomsService: RoomsService,
    private reviewsService: ReviewsService
  ){}

  ngOnInit(): void {
    this.modelType = this.route.snapshot.paramMap.get('id') as ModelType;
    this.modelLayout = ModelLayouts[this.modelType];

    // init subscriptioins
    this.amenitiesService.init();
    this.apartmentsService.init();
    this.roomsService.init();
    this.reviewsService.init();
    this.townsService.init();

    // subscribe to specific data
    switch(this.modelType) {
      case 'town':
        this.townsService.townsSubject$
          .pipe(takeUntil(this.destroy$))
          .subscribe(townsData => {
            this.modelData = townsData;
          });
        break;

      case 'apartment':
        this.apartmentsService.apartmentsSubject$
          .pipe(takeUntil(this.destroy$))
          .subscribe(apartmentsData => {
            this.modelData = apartmentsData;
          })
        break;
      
      case 'amenity':
        this.amenitiesService.amenitiesSubject$
          .pipe(takeUntil(this.destroy$))
          .subscribe(amenitiesData => {
            this.modelData = amenitiesData;
          })
        break;

      case 'room':
        this.roomsService.roomsSubject$
          .pipe(takeUntil(this.destroy$))
          .subscribe(roomsData => {
            this.modelData = roomsData;
          })
        break;

      case 'review':
        this.reviewsService.reviewsSubject$
          .pipe(takeUntil(this.destroy$))
          .subscribe(reviewsData => {
            this.modelData = reviewsData;
          })
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  openDialog(enterAnimationDuration: string, exitAnimationDuration: string, value: any): void {
    this.dialog.open(DialogComponent, {
      width: '400px',
      enterAnimationDuration,
      exitAnimationDuration,
      data: {
        modelName: value.name,
        modelType: this.modelType,
        id: value.id
      }
    });
  }

  openFormDialog(enterAnimationDuration: string, exitAnimationDuration: string, value?: number): void {
    this.dialog.open(FormDialogComponent, {
      width: '400px',
      enterAnimationDuration,
      exitAnimationDuration,
      data: {
        modelType: this.modelType,
        id: value
      }
    });
  }
}
