package higold.by.flatrent.repositories;

import higold.by.flatrent.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatRepository extends JpaRepository<Flat, Long> {
}
