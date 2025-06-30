package mk.rezi.rezimk.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.rezi.rezimk.dto.ReviewDto;
import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.model.Review;
import mk.rezi.rezimk.model.exception.ReviewNotFoundException;
import mk.rezi.rezimk.repository.ReviewRepository;
import mk.rezi.rezimk.service.domain.ApartmentService;
import mk.rezi.rezimk.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ApartmentService apartmentService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ApartmentService apartmentService) {
        this.reviewRepository = reviewRepository;
        this.apartmentService = apartmentService;
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
    @Transactional
    public Review save(ReviewDto reviewDto) {
        Apartment apartment = this.apartmentService.findById(reviewDto.apartmentId());
        return reviewRepository.save(new Review(reviewDto.rating(), reviewDto.comment(), apartment));
    }

    @Override
    @Transactional
    public Review update(Long id, ReviewDto reviewDto) {
        Review oldReview = this.findById(id);
        Apartment apartment = this.apartmentService.findById(reviewDto.apartmentId());

        oldReview.setComment(reviewDto.comment());
        oldReview.setRating(reviewDto.rating());
        oldReview.setApartment(apartment);

        return reviewRepository.save(oldReview);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Review review = this.findById(id);
        if (review == null) {
            throw new ReviewNotFoundException(id);
        }
        reviewRepository.delete(review);
    }
}
