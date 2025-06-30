import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { IAmenity } from '../../utils/project.utils';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-amenity-selector-dialog',
  templateUrl: './amenity-selector-dialog.component.html',
  imports: [CommonModule, FormsModule, MatCheckboxModule, MatDialogModule, MatButtonModule]
})
export class AmenitySelectorDialogComponent {
  selectedIds: number[];

  constructor(
    public dialogRef: MatDialogRef<AmenitySelectorDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { amenities: IAmenity[], preselectedIds: number[] }
  ) {
    this.selectedIds = [...data.preselectedIds];
  }

  toggleAmenity(id: number) {
    if (this.selectedIds.includes(id)) {
      this.selectedIds = this.selectedIds.filter(i => i !== id);
    } else {
      this.selectedIds.push(id);
    }
  }

  isSelected(id: number) {
    return this.selectedIds.includes(id);
  }

  isDisabled(amenityId: number): boolean {
  // Disable checkboxes for preselected amenities to prevent unchecking
  return this.data.preselectedIds.includes(amenityId);
}

  confirm() {
    this.dialogRef.close(this.selectedIds);
  }

  cancel() {
    this.dialogRef.close();
  }
}
