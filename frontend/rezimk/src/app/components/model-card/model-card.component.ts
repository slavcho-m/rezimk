import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

export enum EModelType {
  AMENITY = "amenities",
  APARTMENT = "apartments",
  ROOM = "rooms",
  REVIEW = "reviews",
  TOWN = "towns"
}

@Component({
  selector: 'app-model-card',
  imports: [CommonModule],
  templateUrl: './model-card.component.html',
  styleUrl: './model-card.component.css'
})
export class ModelCardComponent {
  @Input() modelType!: EModelType;
  modelTypeEnum = EModelType;
}
