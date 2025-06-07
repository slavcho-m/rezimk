package mk.rezi.rezimk.repository;

import mk.rezi.rezimk.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
