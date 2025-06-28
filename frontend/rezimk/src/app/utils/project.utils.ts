export interface IAmenity {
  id: number,
  name: string
}

export interface ITown {
  id: number,
  name: string
}

export enum ERoomType {
  SINGLE, DOUBLE, SUITE, STUDIO, FAMILY
}

export interface IRoom {
  id: number,
  roomType: ERoomType,
  capacity: number,
  pricePerNight: number,
  apartment: IApartment,
  amenities: IAmenity[],
}

export enum ERating {
  ONE_STAR, TWO_STAR, THREE_STAR, FOUR_STAR, FIVE_STAR
}

export interface IReview {
  id: number,
  rating: ERating,
  comment: string,
  apartment: IApartment
}

export interface IApartment {
  id: number,
  name: string,
  address: string,
  description: string,
  town: ITown
}

export const ModelLayouts = {
  town: ['id', 'name'] as (keyof ITown)[],
  apartment: ['id', 'address', 'description', 'name', 'town'] as (keyof IApartment)[],
  review: ['id', 'rating', 'comment', 'apartment'] as (keyof IReview)[],
  room: ['id', 'roomType', 'capacity', 'amenities', 'pricePerNight', 'apartment'] as (keyof IRoom)[],
  amenity: ['id', 'name'] as (keyof IAmenity)[]
};

export type ModelType = 'town' | 'apartment' | 'room' | 'review' | 'amenity';

export interface ITownDto {
    name: string
}

export interface IAmenityDto {
    name: string
}

export interface IReviewDto {
    rating: ERating,
    comment: string,
    apartmentId: number
}

export interface IApartmentDto {
    name: string,
    address: string,
    description: string,
    townId: number
}

export interface IRoomDto {
    roomType: ERoomType,
    capacity: number,
    pricePerNight: number,
    apartmentId: number
}

export const ModelDtoLayouts = {
    town: ['name'] as (keyof ITownDto)[],
    apartment: ['name', 'address', 'description', 'townId'] as (keyof IApartmentDto)[],
    room: ['roomType', 'capacity', 'pricePerNight', 'apartmentId'] as (keyof IRoomDto)[],
    review: ['comment', 'rating', 'apartmentId'] as (keyof IReviewDto)[],
    amenity: ['name'] as (keyof IAmenityDto)[],
}