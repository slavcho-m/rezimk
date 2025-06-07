package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.service.domain.ApartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apartments")
@Tag(name = "Apartments API", description = "Endpoint for managing apartments")
public class ApartmentController {
    //TODO: Upgrade to Application Service using DTO's
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<Apartment> getAll() {
        return apartmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getById(@PathVariable long id) {
        return ResponseEntity.ok(apartmentService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Apartment> add(@RequestBody Apartment apartment) {
        return ResponseEntity.ok(apartmentService.save(apartment));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Apartment> edit(@PathVariable long id, @RequestBody Apartment apartment) {
        return ResponseEntity.ok(apartmentService.update(id, apartment));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Apartment> delete(@PathVariable long id) {
        return ResponseEntity.ok(apartmentService.deleteById(id));
    }
}
