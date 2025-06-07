package mk.rezi.rezimk.service.domain;

import java.util.List;
import mk.rezi.rezimk.model.Room;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room save(Room room);
    Room update(Room room);
    Room deleteById(Long id);
}
