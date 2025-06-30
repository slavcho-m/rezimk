import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-advanced-searchbar',
  imports: [CommonModule, FormsModule],
  templateUrl: './advanced-searchbar.component.html',
  styleUrl: './advanced-searchbar.component.css'
})
export class AdvancedSearchbarComponent {
  @Output() searchValue = new EventEmitter<string>();

  inputValue = '';
  onInputChange() {
    this.searchValue.emit(this.inputValue);
  }
}
