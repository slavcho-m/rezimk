package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.dto.RoomDto;
import mk.rezi.rezimk.model.Room;
import mk.rezi.rezimk.service.domain.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Tag(name = "Rooms API", description = "Endpoint for managing rooms")
public class RoomController {
    private final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Room> create(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.save(roomDto));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.update(id, roomDto));
    }

    @PutMapping("/edit/{id}/amenities")
    public ResponseEntity<Void> addAmenities(@PathVariable Long id, @RequestBody List<Long> amenityIds) {
        roomService.addAmenities(id, amenityIds);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reserve/{id}")
    public ResponseEntity<Void> reserve(@PathVariable Long id) {
        roomService.reserveRoom(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/free/{id}")
    public ResponseEntity<Void> free(@PathVariable Long id) {
        roomService.freeRoom(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roomService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
