package mk.rezi.rezimk.service.domain.impl;

import mk.rezi.rezimk.model.Image;
import mk.rezi.rezimk.model.exception.ImageNotFoundException;
import mk.rezi.rezimk.repository.ImageRepository;
import mk.rezi.rezimk.service.domain.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long id) {
        return this.imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Long id, Image image) {
        Image old = this.findById(id);
        if (old == null) {
            throw new ImageNotFoundException(image.getId());
        }

        old.setImageType(image.getImageType());
        old.setName(image.getName());
        old.setData(image.getData());

        if (image.getApartment() != null) {
            old.setApartment(image.getApartment());
        }

        if (image.getRoom() != null) {
            old.setRoom(image.getRoom());
        }

        return imageRepository.save(old);
    }

    @Override
    public Image deleteById(Long id) {
        Image image = this.findById(id);
        if (image == null) {
            throw new ImageNotFoundException(id);
        }
        this.imageRepository.delete(image);
        return image;
    }
}
