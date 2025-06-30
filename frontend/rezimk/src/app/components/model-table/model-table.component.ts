import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CamelToTitlePipe } from '../../utils/camel-to-title.pipe';

@Component({
  selector: 'app-model-table',
  imports: [CommonModule, CamelToTitlePipe],
  templateUrl: './model-table.component.html',
  styleUrl: './model-table.component.css'
})
export class ModelTableComponent {
  @Input() headers: string[] = [];
  @Input() rows: any[] = [];

  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();

  onEdit(id: number) {
    this.edit.emit(id);
  }

  onDelete(id: number, name: string) {
    this.delete.emit({id: id, name: name});
  }

  resolveValue(value: any): string {
    if (value === null || value === undefined) {
      return '';
    }

    if (typeof value !== 'object') {
      return value;
    }

    if (Array.isArray(value)) {
      return value.map(v => this.resolveValue(v)).join(', ');
    }

    const possibleKeys = ['name', 'roomType', 'rating'];
    for (const key of possibleKeys) {
      if (key in value) {
        return value[key];
      }
    }

    return '[Object]';
  }
}
