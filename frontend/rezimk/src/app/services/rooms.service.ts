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
    await this.http.get<IRoom[]>('http://localhost:8080/api/rooms')
      .subscribe(data => {
        this.rooms = data;
        this.roomsSubject.next(this.rooms);
      })
  }

  fetchRoomById(id: number) {
    return this.http.get<IRoom>(`http://localhost:8080/api/rooms/${id}`);
  }

  addRoom(roomDto: IRoomDto) {
    this.http.post<IRoom>('http://localhost:8080/api/rooms/add', roomDto)
      .subscribe((createdRoom) => {
        this.rooms.push(createdRoom);
        this.roomsSubject.next([...this.rooms]);
      });
  }

  editRoom(id: number, roomDto: IRoomDto) {
    this.http.put<IRoom>(`http://localhost:8080/api/rooms/edit/${id}`, roomDto)
      .subscribe((updatedRoom) => {
        const index = this.rooms.findIndex(r => r.id === id);
        if (index > -1) {
          this.rooms[index] = updatedRoom;
          this.roomsSubject.next([...this.rooms]);
        }
      });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
}
