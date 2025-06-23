package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.dto.AmenityDto;
import mk.rezi.rezimk.model.Amenity;
import mk.rezi.rezimk.repository.AmenityRepository;
import mk.rezi.rezimk.service.domain.AmenityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    public AmenityServiceImpl(AmenityRepository amenityRepository) {
        this.amenityRepository = amenityRepository;
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
    public Amenity save(AmenityDto amenityDto) {
        return amenityRepository.save(new Amenity(amenityDto.name()));
    }

    @Override
    public Amenity update(Long id, AmenityDto amenityDto) {
        Amenity updatedAmenity = this.findById(id);
        updatedAmenity.setName(amenityDto.name());
        return amenityRepository.save(updatedAmenity);
    }

    @Override
    public void deleteById(Long id) {
        amenityRepository.deleteById(id);
    }
}
