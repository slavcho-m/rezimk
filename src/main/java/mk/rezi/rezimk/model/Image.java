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
    @Column(name = "data", columnDefinition = "bytea")
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

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
