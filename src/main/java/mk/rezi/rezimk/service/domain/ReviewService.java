package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    Review findById(Long id);
    Review save(Review review);
    Review update(Long id, Review review);
    Review deleteById(Long id);
}
