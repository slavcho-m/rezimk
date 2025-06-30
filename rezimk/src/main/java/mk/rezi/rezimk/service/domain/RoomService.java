package mk.rezi.rezimk.service.domain;

import java.util.List;

import mk.rezi.rezimk.dto.RoomDto;
import mk.rezi.rezimk.model.Room;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room save(RoomDto roomDto);
    Room update(Long id, RoomDto roomDto);
    void deleteById(Long id);
    void addAmenities(Long roomId, List<Long> amenityIds);
    void reserveRoom(Long id);
    void freeRoom(Long id);
}
