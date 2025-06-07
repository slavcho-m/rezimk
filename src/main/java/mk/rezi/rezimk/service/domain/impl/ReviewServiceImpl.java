package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.model.Review;
import mk.rezi.rezimk.model.exception.ReviewNotFoundException;
import mk.rezi.rezimk.repository.ReviewRepository;
import mk.rezi.rezimk.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(Long id) {
        return this.reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Review review) {
        Review oldReview = this.findById(review.getId());
        if (oldReview == null) {
            throw new ReviewNotFoundException(review.getId());
        }

        oldReview.setComment(review.getComment());
        oldReview.setRating(review.getRating());
        oldReview.setApartment(review.getApartment());

        return reviewRepository.save(oldReview);
    }

    @Override
    public Review deleteById(Long id) {
        Review review = this.findById(id);
        if (review == null) {
            throw new ReviewNotFoundException(id);
        }
        reviewRepository.delete(review);
        return review;
    }
}
