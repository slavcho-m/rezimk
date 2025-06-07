package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.model.exception.ApartmentNotFoundException;
import mk.rezi.rezimk.repository.ApartmentRepository;
import mk.rezi.rezimk.service.domain.ApartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Apartment> findAll() {
        return this.apartmentRepository.findAll();
    }

    @Override
    public Apartment findById(Long id) {
        return this.apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(id));
    }

    @Override
    public Apartment save(Apartment apartment) {
        return this.apartmentRepository.save(apartment);
    }

    @Override
    public Apartment update(Apartment apartment) {
        Apartment old = this.findById(apartment.getId());

        if (old != null) {
            old.setName(apartment.getName());
            old.setAddress(apartment.getAddress());
            old.setDescription(apartment.getDescription());
            old.setTown(apartment.getTown());

            if (!apartment.getRooms().isEmpty()) {
                old.setRooms(apartment.getRooms());
            }

            if (!apartment.getReviews().isEmpty()) {
                old.setReviews(apartment.getReviews());
            }

            if (!apartment.getImages().isEmpty()) {
                old.setImages(apartment.getImages());
            }
        } else {
            throw new ApartmentNotFoundException(apartment.getId());
        }

        return this.apartmentRepository.save(old);
    }

    @Override
    public Apartment deleteById(Long id) {
        Apartment apartment = this.findById(id);
        if (apartment == null) {
            throw new ApartmentNotFoundException(id);
        }
        this.apartmentRepository.delete(apartment);

        return apartment;
    }
}
