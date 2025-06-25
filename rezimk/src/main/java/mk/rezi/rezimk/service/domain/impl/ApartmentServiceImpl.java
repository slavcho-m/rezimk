package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.dto.ApartmentDto;
import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.model.Town;
import mk.rezi.rezimk.model.exception.ApartmentNotFoundException;
import mk.rezi.rezimk.repository.ApartmentRepository;
import mk.rezi.rezimk.service.domain.ApartmentService;
import mk.rezi.rezimk.service.domain.TownService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final TownService townService;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, TownService townService) {
        this.apartmentRepository = apartmentRepository;
        this.townService = townService;
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
    public Apartment save(ApartmentDto apartmentDto) {
        Town town = townService.findById(apartmentDto.townId());
        return this.apartmentRepository.save(new Apartment(apartmentDto.name(), apartmentDto.address(), apartmentDto.description(), town));
    }

    @Override
    public Apartment update(Long id, ApartmentDto apartmentDto) {
        Apartment old = this.findById(id);
        Town town = townService.findById(apartmentDto.townId());

        if (old != null) {
            old.setName(apartmentDto.name());
            old.setAddress(apartmentDto.address());
            old.setDescription(apartmentDto.description());
            old.setTown(town);

        } else {
            throw new ApartmentNotFoundException(id);
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
