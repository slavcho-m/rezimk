package mk.rezi.rezimk.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.rezi.rezimk.dto.AmenityDto;
import mk.rezi.rezimk.model.Amenity;
import mk.rezi.rezimk.model.Room;
import mk.rezi.rezimk.repository.AmenityRepository;
import mk.rezi.rezimk.repository.RoomRepository;
import mk.rezi.rezimk.service.domain.AmenityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final RoomRepository roomRepository;

    public AmenityServiceImpl(AmenityRepository amenityRepository, RoomRepository roomRepository) {
        this.amenityRepository = amenityRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    @Override
    public Amenity findById(Long id) {
        return amenityRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Amenity save(AmenityDto amenityDto) {
        return amenityRepository.save(new Amenity(amenityDto.name()));
    }

    @Override
    @Transactional
    public Amenity update(Long id, AmenityDto amenityDto) {
        Amenity updatedAmenity = this.findById(id);
        updatedAmenity.setName(amenityDto.name());
        return amenityRepository.save(updatedAmenity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Amenity amenity = findById(id);
        List<Room> roomsWithAmenity = roomRepository.findByAmenitiesContaining(amenity);

        for (Room room : roomsWithAmenity) {
            room.getAmenities().remove(amenity);
        }

        amenityRepository.delete(amenity);
    }
}
