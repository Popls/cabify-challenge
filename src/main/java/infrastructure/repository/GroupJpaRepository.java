package infrastructure.repository;

import infrastructure.entity.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupJpaRepository extends CrudRepository<GroupEntity, Integer> {

}
