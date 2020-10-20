package infrastructure.repository.impl;

import java.util.Optional;

import domain.model.Group;
import domain.model.Journey;
import domain.repository.JourneyRepository;
import infrastructure.mapper.JourneyEntityMapper;
import infrastructure.repository.JourneyJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JourneyRepositoryImpl implements JourneyRepository {

  @Autowired
  private JourneyJpaRepository journeyJpaRepository;
  @Autowired
  private JourneyEntityMapper journeyEntityMapper;

  @Override
  public Optional<Journey> find(Group group) {
    return journeyJpaRepository.findByGroupEntityId(group.getId())
        .map(journeyEntity -> journeyEntityMapper.toJourney(journeyEntity));
  }

  @Override
  public Journey save(Journey journey) {
    return journeyEntityMapper
        .toJourney(journeyJpaRepository.save(journeyEntityMapper.toJourneyEntity(journey)));
  }

  @Override
  public void remove(Journey journey) {
    journeyJpaRepository.delete(journeyEntityMapper.toJourneyEntity(journey));
  }
}
