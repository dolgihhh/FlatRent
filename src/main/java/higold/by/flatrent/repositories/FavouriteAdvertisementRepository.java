package higold.by.flatrent.repositories;

import higold.by.flatrent.entities.Advertisement;
import higold.by.flatrent.entities.FavouriteAdvertisement;
import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavouriteAdvertisementRepository extends JpaRepository<FavouriteAdvertisement, Long> {
    Optional<FavouriteAdvertisement> findByUserAndAdvertisement(User user,
                                                                Advertisement advertisement);

    Boolean existsByUserAndAdvertisement(User user, Advertisement advertisement);
}
