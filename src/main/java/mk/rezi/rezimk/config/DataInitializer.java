package mk.rezi.rezimk.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mk.rezi.rezimk.model.Apartment;
import mk.rezi.rezimk.model.Review;
import mk.rezi.rezimk.model.Room;
import mk.rezi.rezimk.model.Town;
import mk.rezi.rezimk.model.enums.AmenityType;
import mk.rezi.rezimk.model.enums.Rating;
import mk.rezi.rezimk.model.enums.RoomType;
import mk.rezi.rezimk.repository.ApartmentRepository;
import mk.rezi.rezimk.repository.ReviewRepository;
import mk.rezi.rezimk.repository.RoomRepository;
import mk.rezi.rezimk.repository.TownRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final TownRepository townRepository;
    private final ApartmentRepository apartmentRepository;
    private final RoomRepository roomRepository;
    private final ReviewRepository reviewRepository;

    public DataInitializer(TownRepository townRepository, ApartmentRepository apartmentRepository, RoomRepository roomRepository, ReviewRepository reviewRepository) {
        this.townRepository = townRepository;
        this.apartmentRepository = apartmentRepository;
        this.roomRepository = roomRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (townRepository.count() > 0) return; // Prevent running twice

        List<Town> towns = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Town town = new Town("Town " + i);
            towns.add(townRepository.save(town));
        }

        for (int i = 1; i <= 10; i++) {
            Town town = towns.get(i % towns.size());

            Apartment apartment = new Apartment(
                    "Apartment " + i,
                    "Address " + i,
                    "Nice place to stay, number " + i,
                    town
            );
            apartment = apartmentRepository.save(apartment);

            // Add 2 rooms per apartment
            for (int r = 1; r <= 2; r++) {
                Room room = new Room(
                        RoomType.values()[r % RoomType.values().length],
                        2 + r,
                        BigDecimal.valueOf(30 + r * 10),
                        apartment,
                        List.of(AmenityType.WIFI, AmenityType.AIR_CONDITIONING)
                );
                roomRepository.save(room);
            }

            // Add 1-2 reviews
            for (int r = 0; r < 2; r++) {
                Review review = new Review(
                        Rating.values()[new Random().nextInt(Rating.values().length)],
                        "This is review " + r + " for apartment " + i,
                        apartment
                );
                reviewRepository.save(review);
            }
        }

        System.out.println("📦 Sample data initialized.");
    }
}