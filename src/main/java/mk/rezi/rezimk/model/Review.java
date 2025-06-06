package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.rezi.rezimk.model.enums.Rating;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    private String comment;

    //TODO: add user and add manyToOne relation to user

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    public Review() {}

    public Review(Rating rating, String comment, Apartment apartment) {
        this.rating = rating;
        this.comment = comment;
        this.apartment = apartment;
    }
}
