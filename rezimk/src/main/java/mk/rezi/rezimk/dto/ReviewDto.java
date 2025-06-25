package mk.rezi.rezimk.dto;

import mk.rezi.rezimk.model.enums.Rating;

public record ReviewDto(Rating rating, String comment, Long apartmentId) {
}
