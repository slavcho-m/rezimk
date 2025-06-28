import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'camelToTitle',
  standalone: true
})
export class CamelToTitlePipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return '';

    // Add space before capital letters & make lowercase letters title case
    const spaced = value.replace(/([A-Z])/g, ' $1');
    return spaced.charAt(0).toUpperCase() + spaced.slice(1).toLowerCase();
  }
}
