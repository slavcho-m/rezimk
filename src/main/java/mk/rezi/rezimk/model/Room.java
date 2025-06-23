package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;
import mk.rezi.rezimk.model.enums.RoomType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany
    private List<Amenity> amenities;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public Room() {}

    public Room(RoomType roomType, int capacity, BigDecimal pricePerNight, Apartment apartment) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.apartment = apartment;
        this.amenities = new ArrayList<>();
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

    public void addAmenities(List<Amenity> amenities) {
        this.amenities.addAll(amenities);
    }

    public void removeAmenity(Amenity amenity) {
        this.amenities.remove(amenity);
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

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
