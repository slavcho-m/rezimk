package mk.rezi.rezimk.repository;

import mk.rezi.rezimk.model.Amenity;
import mk.rezi.rezimk.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAmenitiesContaining(Amenity amenity);
}
