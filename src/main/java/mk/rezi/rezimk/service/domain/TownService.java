package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.dto.TownDto;
import mk.rezi.rezimk.model.Town;

import java.util.List;

public interface TownService {
    List<Town> findAll();
    Town findById(Long id);
    Town findByName(String name);
    Town save(TownDto townDto);
    Town update(Long id, TownDto townDto);
    Town deleteById(Long id);
}
