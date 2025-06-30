import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export const api_weather_key = "106f401a545d458b93e00711253006";

export interface WeatherResponseDto {
  location: {
    name: string;
    region: string;
    country: string;
    lat: number;
    lon: number;
    tz_id: string;
    localtime_epoch: number;
    localtime: string;
  };
  current: {
    last_updated_epoch: number;
    last_updated: string;
    temp_c: number;
    temp_f: number;
    is_day: number;
    condition: {
      text: string;
      icon: string;
      code: number;
    };
    wind_mph: number;
    wind_kph: number;
    wind_degree: number;
    wind_dir: string;
    pressure_mb: number;
    pressure_in: number;
    precip_mm: number;
    precip_in: number;
    humidity: number;
    cloud: number;
    feelslike_c: number;
    feelslike_f: number;
    windchill_c: number;
    windchill_f: number;
    heatindex_c: number;
    heatindex_f: number;
    dewpoint_c: number;
    dewpoint_f: number;
    vis_km: number;
    vis_miles: number;
    uv: number;
    gust_mph: number;
    gust_kph: number;
  };
}

export interface WeatherTransformDto {
  name: string;
  country: string;
  last_updated: string;
  temp_c: number;
  condition: {
    text: string;
    icon: string;
    code: number;
  };
  humidity: number;
}


@Injectable({
  providedIn: 'root'
})
export class WeatherApiService {
  weatherData!: WeatherTransformDto;
  weatherDataSubject = new Subject<WeatherTransformDto>();
  weatherDataSubject$ = this.weatherDataSubject.asObservable();

  constructor( private http: HttpClient ) { }

  private url = "http://api.weatherapi.com/v1/current.json?key=106f401a545d458b93e00711253006&q=Skopje&aqi=no";

  init() {
    this.http.get<WeatherResponseDto>(this.url)
      .subscribe((weatherResponseData) => {
        this.weatherData = this.transformWeather(weatherResponseData);
        this.weatherDataSubject.next(this.weatherData);
      })
  }


  transformWeather(data: WeatherResponseDto): WeatherTransformDto {
    return {
      name: data.location.name,
      country: data.location.country,
      last_updated: data.current.last_updated,
      temp_c: data.current.temp_c,
      condition: {
        text: data.current.condition.text,
        icon: data.current.condition.icon,
        code: data.current.condition.code
      },
      humidity: data.current.humidity
    };
  }
}
