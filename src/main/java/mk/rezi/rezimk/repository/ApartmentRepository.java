package mk.rezi.rezimk.repository;

import mk.rezi.rezimk.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
