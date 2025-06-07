package mk.rezi.rezimk.repository;

import mk.rezi.rezimk.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByName(String name);
}
