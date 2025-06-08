package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.rezi.rezimk.model.enums.AmenityType;
import mk.rezi.rezimk.model.enums.RoomType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private int capacity;
    private BigDecimal pricePerNight;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @ElementCollection(targetClass = AmenityType.class)
    @CollectionTable(name = "room_amenities", joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private List<AmenityType> amenities;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public Room() {}

    public Room(RoomType roomType, int capacity, BigDecimal pricePerNight, Apartment apartment, List<AmenityType> amenities) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.apartment = apartment;
        this.amenities = amenities;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void addMultipleImages(List<Image> images) {
        this.images.addAll(images);
    }

    public void removeImage(Image image) {
        this.images.remove(image);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public List<AmenityType> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenityType> amenities) {
        this.amenities = amenities;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
