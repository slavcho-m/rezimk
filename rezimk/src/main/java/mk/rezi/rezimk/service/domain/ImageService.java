package mk.rezi.rezimk.service.domain;

import mk.rezi.rezimk.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> findAll();
    Image findById(Long id);
    Image save(Image image);
    Image update(Long id, Image image);
    Image deleteById(Long id);
}
