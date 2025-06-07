package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.model.Town;

import java.util.List;

public interface TownService {
    List<Town> findAll();
    Town findById(Long id);
    Town findByName(String name);
    Town save(Town town);
    Town update(Long id, Town town);
    Town deleteById(Long id);
}
