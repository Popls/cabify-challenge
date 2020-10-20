package infrastructure.repository;

import java.util.List;

import infrastructure.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarJpaRepository extends CrudRepository<CarEntity, Integer> {

  List<CarEntity> findBySeatsGreaterThanEqual(Integer seats);

}
