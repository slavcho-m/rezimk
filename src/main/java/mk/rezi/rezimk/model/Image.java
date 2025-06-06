package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Image() {}
    public Image(String url) {
        this.url = url;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
