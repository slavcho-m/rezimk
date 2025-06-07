package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.rezi.rezimk.model.enums.ImageType;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ImageType imageType;

    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Image() {}

    // Constructor for just uploading an image
    public Image(String name, byte imageData, ImageType imageType) {
        this.data = new byte[]{imageData};
        this.imageType = imageType;
        this.name = name;
    }

    // Constructor for uploading an image for an apartment
    public Image(String name, byte imageData, ImageType imageType, Apartment apartment) {
        this.data = new byte[]{imageData};
        this.imageType = imageType;
        this.apartment = apartment;
        this.name = name;
    }

    // Constructor for uploading an image for a room
    public Image(String name, byte imageData, ImageType imageType, Room room) {
        this.data = new byte[]{imageData};
        this.imageType = imageType;
        this.room = room;
        this.name = name;
    }
}
