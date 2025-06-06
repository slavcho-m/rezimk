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
}
