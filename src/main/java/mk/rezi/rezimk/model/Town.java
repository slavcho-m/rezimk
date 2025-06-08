package mk.rezi.rezimk.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "town")
    List<Apartment> apartments = new ArrayList<>();

    public Town() {}

    public Town(String name) {
        this.name = name;
    }

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }

    public void removeApartment(Apartment apartment) {
        this.apartments.remove(apartment);
    }

    // Getters and setters

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
