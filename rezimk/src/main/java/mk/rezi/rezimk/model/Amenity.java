package mk.rezi.rezimk.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Amenity() {}
    public Amenity(String name) {
        this.name = name;
    }

    // getters and setters


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
