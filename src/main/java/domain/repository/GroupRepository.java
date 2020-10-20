package domain.repository;

import java.util.Optional;

import domain.model.Group;

public interface GroupRepository {

  Optional<Group> find(Group group);

  Group save(Group group);

  void remove(Group group);

}
