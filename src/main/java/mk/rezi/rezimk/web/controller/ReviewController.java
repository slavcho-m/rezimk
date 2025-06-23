package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.dto.ReviewDto;
import mk.rezi.rezimk.model.Review;
import mk.rezi.rezimk.service.domain.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Reviews API", description = "Endpoint for managing reviews")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Review> add(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.save(reviewDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Review> edit(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(id, reviewDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Review> delete(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.deleteById(id));
    }
}
