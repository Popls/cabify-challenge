package infrastructure.repository;

import java.util.Optional;

import infrastructure.entity.JourneyEntity;
import org.springframework.data.repository.CrudRepository;

public interface JourneyJpaRepository extends CrudRepository<JourneyEntity, Integer> {

  Optional<JourneyEntity> findByGroupEntityId(Integer id);

}
