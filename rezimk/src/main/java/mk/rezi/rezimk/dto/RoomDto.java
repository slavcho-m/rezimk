package mk.rezi.rezimk.dto;

import mk.rezi.rezimk.model.enums.RoomType;

import java.math.BigDecimal;

public record RoomDto(RoomType roomType, int capacity, BigDecimal pricePerNight, Long apartmentId) {
}
