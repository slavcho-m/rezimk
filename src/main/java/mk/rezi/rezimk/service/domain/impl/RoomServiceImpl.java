package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.dto.RoomDto;
import mk.rezi.rezimk.model.Amenity;
import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.model.Room;
import mk.rezi.rezimk.model.exception.RoomNotFoundException;
import mk.rezi.rezimk.repository.RoomRepository;
import mk.rezi.rezimk.service.domain.AmenityService;
import mk.rezi.rezimk.service.domain.ApartmentService;
import mk.rezi.rezimk.service.domain.RoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ApartmentService apartmentService;
    private final AmenityService amenityService;

    public RoomServiceImpl(RoomRepository roomRepository, ApartmentService apartmentService, AmenityService amenityService) {
        this.roomRepository = roomRepository;
        this.apartmentService = apartmentService;
        this.amenityService = amenityService;
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
    public Room save(RoomDto roomDto) {
        Apartment apartment = apartmentService.findById(roomDto.apartmentId());
        return this.roomRepository.save(
                new Room(
                        roomDto.roomType(),
                        roomDto.capacity(),
                        roomDto.pricePerNight(),
                        apartment
                )
        );
    }

    @Override
    public Room update(Long id, RoomDto roomDto) {
        Room old = this.findById(id);
        Apartment apartment = apartmentService.findById(id);

        if (old == null) {
            throw new RoomNotFoundException(id);
        }

        old.setCapacity(roomDto.capacity());
        old.setPricePerNight(roomDto.pricePerNight());
        old.setRoomType(roomDto.roomType());

        if (apartment != null) {
            old.setApartment(apartment);
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

    @Override
    public void addAmenities(Long roomId, List<Long> amenityIds) {
        Room room = this.findById(roomId);
        List<Amenity> amenities = new ArrayList<>();

        for (Long amenityId : amenityIds) {
            Amenity amenity = this.amenityService.findById(amenityId);
            amenities.add(amenity);
        }

        room.addAmenities(amenities);
        roomRepository.save(room);
    }
}
