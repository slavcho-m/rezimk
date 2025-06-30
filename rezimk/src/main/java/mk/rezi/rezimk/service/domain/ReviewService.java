package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.dto.ReviewDto;
import mk.rezi.rezimk.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    Review findById(Long id);
    Review save(ReviewDto reviewDto);
    Review update(Long id, ReviewDto reviewDto);
    void deleteById(Long id);
}
