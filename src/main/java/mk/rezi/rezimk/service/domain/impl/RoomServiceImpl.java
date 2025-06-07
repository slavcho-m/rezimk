package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.model.Room;
import mk.rezi.rezimk.model.exception.RoomNotFoundException;
import mk.rezi.rezimk.repository.RoomRepository;
import mk.rezi.rezimk.service.domain.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return this.roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public Room save(Room room) {
        return this.roomRepository.save(room);
    }

    @Override
    public Room update(Long id, Room room) {
        Room old = this.findById(id);
        if (old == null) {
            throw new RoomNotFoundException(room.getId());
        }

        old.setCapacity(room.getCapacity());
        old.setPricePerNight(room.getPricePerNight());
        old.setRoomType(room.getRoomType());

        if (!room.getAmenities().isEmpty()) {
            old.setAmenities(room.getAmenities());
        }

        if (!room.getImages().isEmpty()) {
            old.setImages(room.getImages());
        }

        if (room.getApartment() != null) {
            old.setApartment(room.getApartment());
        }

        return roomRepository.save(old);
    }

    @Override
    public Room deleteById(Long id) {
        Room room = this.findById(id);
        if (room == null) {
            throw new RoomNotFoundException(id);
        }
        this.roomRepository.delete(room);
        return room;
    }
}
