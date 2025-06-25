import { Component } from '@angular/core';
import { EModelType, ModelCardComponent } from '../../components/model-card/model-card.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-models',
  imports: [ModelCardComponent, CommonModule],
  templateUrl: './edit-models.component.html',
  styleUrl: './edit-models.component.css'
})
export class EditModelsComponent {
  modelTypeEnum = EModelType;

  constructor(private router: Router, private route: ActivatedRoute) {}

  onCardClick(value: EModelType) {
    this.router.navigate(['model', value], { relativeTo: this.route });
  }
}
