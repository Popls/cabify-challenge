package domain.repository;

import java.util.Optional;

import domain.model.Group;
import domain.model.Journey;

public interface JourneyRepository {

  Optional<Journey> find(Group group);

  Journey save(Journey journey);

  void remove(Journey journey);

}
