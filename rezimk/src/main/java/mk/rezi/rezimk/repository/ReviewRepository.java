package mk.rezi.rezimk.repository;

import mk.rezi.rezimk.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
