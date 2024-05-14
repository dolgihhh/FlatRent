package higold.by.flatrent.repositories;

import higold.by.flatrent.entities.Advertisement;
import higold.by.flatrent.entities.User;
import higold.by.flatrent.enums.AdvStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByUserAndAdvStatus(User user, AdvStatus advStatus);
    List<Advertisement> findByAdvStatus(AdvStatus status);

    Optional<Advertisement> findByIdAndAdvStatus(Long id, AdvStatus advStatus);
}
