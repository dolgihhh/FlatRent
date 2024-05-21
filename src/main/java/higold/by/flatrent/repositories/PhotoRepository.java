package higold.by.flatrent.repositories;

import higold.by.flatrent.entities.Flat;
import higold.by.flatrent.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
