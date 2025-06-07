package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.model.Town;
import mk.rezi.rezimk.model.exception.TownNotFoundException;
import mk.rezi.rezimk.model.exception.TownWithNameNotFoundException;
import mk.rezi.rezimk.repository.TownRepository;
import mk.rezi.rezimk.service.domain.TownService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<Town> findAll() {
        return townRepository.findAll();
    }

    @Override
    public Town findById(Long id) {
        return townRepository.findById(id).orElseThrow(() -> new TownNotFoundException(id));
    }

    @Override
    public Town findByName(String name) {
        Town town = townRepository.findByName(name);
        if (town == null) {
            throw new TownWithNameNotFoundException(name);
        }

        return town;
    }

    @Override
    public Town save(Town town) {
        return townRepository.save(town);
    }

    @Override
    public Town update(Long id, Town town) {
        Town old = this.findById(id);

        if (old == null) {
            throw new TownNotFoundException(town.getId());
        }

        old.setName(town.getName());

        if (!town.getApartments().isEmpty()) {
            old.setApartments(town.getApartments());
        }

        return townRepository.save(old);
    }

    @Override
    public Town deleteById(Long id) {
        Town town = this.findById(id);
        if (town == null) {
            throw new TownNotFoundException(id);
        }
        townRepository.delete(town);

        return town;
    }
}
