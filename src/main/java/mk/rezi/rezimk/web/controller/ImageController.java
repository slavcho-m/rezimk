package mk.rezi.rezimk.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.rezi.rezimk.model.Image;
import mk.rezi.rezimk.service.domain.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@Tag(name = "Images API", description = "Endpoint for managing images")
public class ImageController {
    private final ImageService imageService;
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getAllImages() {
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.findById(id));
    }

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestBody Image image) {
        return ResponseEntity.ok(imageService.save(image));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Image> editImage(@PathVariable Long id, @RequestBody Image image) {
        return ResponseEntity.ok(imageService.update(id, image));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Image> deleteImage(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.deleteById(id));
    }
}
