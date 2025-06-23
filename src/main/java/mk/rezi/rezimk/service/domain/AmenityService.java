package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.dto.AmenityDto;
import mk.rezi.rezimk.model.Amenity;

import java.util.List;

public interface AmenityService {
    List<Amenity> findAll();
    Amenity findById(Long id);
    Amenity save(AmenityDto amenityDto);
    Amenity update(Long id, AmenityDto amenityDto);
    void deleteById(Long id);
}
