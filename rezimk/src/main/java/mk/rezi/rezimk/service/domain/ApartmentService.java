package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.dto.ApartmentDto;
import mk.rezi.rezimk.model.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> findAll();
    Apartment findById(Long id);
    Apartment save(ApartmentDto apartmentDto);
    Apartment update(Long id, ApartmentDto apartmentDto);
    Apartment deleteById(Long id);
}
