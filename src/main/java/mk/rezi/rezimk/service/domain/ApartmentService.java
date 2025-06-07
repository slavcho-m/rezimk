package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.model.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> findAll();
    Apartment findById(Long id);
    Apartment save(Apartment apartment);
    Apartment update(Long id, Apartment apartment);
    Apartment deleteById(Long id);
}
