import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { interval, Subject, switchMap, takeUntil } from 'rxjs';
import { IRoom, IRoomDto, ITown } from '../utils/project.utils';

@Injectable({
  providedIn: 'root'
})
export class RoomsService implements OnDestroy{
  rooms = [] as IRoom[]
  roomsSubject = new Subject<IRoom[]>();
  roomsSubject$ = this.roomsSubject.asObservable();

  private destroy$ = new Subject<void>();

  constructor( private http: HttpClient ) { }

  async init() {
    await this.http.get<IRoom[]>('/api/rooms')
      .subscribe(data => {
        this.rooms = data;
        this.roomsSubject.next(this.rooms);
      })
  }

  fetchRoomById(id: number) {
    return this.http.get<IRoom>(`/api/rooms/${id}`);
  }

  addRoom(roomDto: IRoomDto) {
    this.http.post<IRoom>('/api/rooms/add', roomDto)
      .subscribe((createdRoom) => {
        this.rooms.push(createdRoom);
        this.roomsSubject.next([...this.rooms]);
      });
  }

  editRoom(id: number, roomDto: IRoomDto) {
    this.http.put<IRoom>(`/api/rooms/edit/${id}`, roomDto)
      .subscribe((updatedRoom) => {
        const index = this.rooms.findIndex(r => r.id === id);
        if (index > -1) {
          this.rooms[index] = updatedRoom;
          this.roomsSubject.next([...this.rooms]);
        }
      });
  }

  addAmenities(id: number, amenityIds: number[]) {
    this.http.put(`/api/rooms/edit/${id}/amenities`, amenityIds)
      .subscribe(() => {
        this.init();
      });
  }

  reserveRoom(id: number) {
    this.http.put(`/api/rooms/reserve/${id}`, null)
      .subscribe(() => {
        this.init();
      })
  }

  freeRoom(id: number) {
    this.http.put(`/api/rooms/free/${id}`, null)
      .subscribe(() => {
        this.init();
      })
  }

  deleteRoom(id: number) {
    this.http.delete(`/api/rooms/delete/${id}`)
    .subscribe(() => {
      this.rooms = this.rooms.filter(room => room.id !== id);
      this.roomsSubject.next([...this.rooms]);
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
}
