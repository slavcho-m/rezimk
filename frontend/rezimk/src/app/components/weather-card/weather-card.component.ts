import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WeatherApiService, WeatherTransformDto } from '../../services/weather-api.service';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-weather-card',
  standalone: true,
  imports: [CommonModule, MatCardModule],
  templateUrl: './weather-card.component.html',
  styleUrl: './weather-card.component.css'
})
export class WeatherCardComponent implements OnInit {
  weather?: WeatherTransformDto;

  constructor(private weatherService: WeatherApiService) {}

  ngOnInit(): void {
    this.weatherService.weatherDataSubject$.subscribe(data => {
      this.weather = data;
    });

    // Optional: trigger fetch here
    this.weatherService.init();
  }
}