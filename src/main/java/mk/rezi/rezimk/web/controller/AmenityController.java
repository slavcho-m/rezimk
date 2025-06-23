package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.dto.AmenityDto;
import mk.rezi.rezimk.model.Amenity;
import mk.rezi.rezimk.service.domain.AmenityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amenities")
@Tag(name = "Amenities API", description = "Endpoint for amenities rooms")
public class AmenityController {
    private final AmenityService amenityService;
    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping
    public List<Amenity> getAllAmenities() {
        return amenityService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amenity> getAmenityById(@PathVariable Long id) {
        return ResponseEntity.ok(amenityService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Amenity> createAmenity(@RequestBody AmenityDto amenityDto) {
        return ResponseEntity.ok(amenityService.save(amenityDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Amenity> updateAmenity(@PathVariable Long id, @RequestBody AmenityDto amenityDto) {
        return ResponseEntity.ok(amenityService.update(id, amenityDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
