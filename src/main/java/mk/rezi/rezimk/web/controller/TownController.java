package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.dto.TownDto;
import mk.rezi.rezimk.model.Town;
import mk.rezi.rezimk.service.domain.TownService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/towns")
@Tag(name = "Towns API", description = "Endpoint for managing towns")
public class TownController {
    private final TownService townService;
    public TownController(TownService townService) {
        this.townService = townService;
    }

    @GetMapping
    public List<Town> getAll() {
        return townService.findAll();
    }

    @GetMapping("/search")
    public ResponseEntity<Town> findByName(@RequestParam String name) {
        return ResponseEntity.ok(townService.findByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Town> getById(@PathVariable Long id) {
        return ResponseEntity.ok(townService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Town> create(@RequestBody TownDto townDto) {
        return ResponseEntity.ok(townService.save(townDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Town> update(@PathVariable Long id, @RequestBody TownDto townDto) {
        return ResponseEntity.ok(townService.update(id, townDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Town> delete(@PathVariable Long id) {
        return ResponseEntity.ok(townService.deleteById(id));
    }
}
