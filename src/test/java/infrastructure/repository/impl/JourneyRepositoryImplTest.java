package infrastructure.repository.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Group;
import domain.model.Journey;
import infrastructure.entity.JourneyEntity;
import infrastructure.mapper.JourneyEntityMapper;
import infrastructure.repository.JourneyJpaRepository;
import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JourneyRepositoryImplTest {

  private JourneyJpaRepository journeyJpaRepository = Mockito.mock(JourneyJpaRepository.class);
  private JourneyEntityMapper journeyEntityMapper = Mockito.mock(JourneyEntityMapper.class);
  private JourneyRepositoryImpl journeyRepository;
  private JourneyEntity journeyEntity;
  private Journey journey;
  private ObjectMapper objectMapper;

  public JourneyRepositoryImplTest() {
    journeyRepository = new JourneyRepositoryImpl(journeyJpaRepository, journeyEntityMapper);
    journeyEntity = JourneyEntity.builder().id(1).build();
    journey = Journey.builder().id(1).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void find() throws JsonProcessingException, JSONException {
    Group group = Group.builder().id(1).people(4).build();
    when(journeyJpaRepository.findByGroupEntityId(any())).thenReturn(Optional.of(journeyEntity));
    when(journeyEntityMapper.toJourney(any())).thenReturn(journey);
    Optional<Journey> result = journeyRepository.find(group);
    verify(journeyJpaRepository,times(1)).findByGroupEntityId(any());
    verify(journeyEntityMapper,times(1)).toJourney(any());
    assertTrue(result.isPresent());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(journey),
        objectMapper.writeValueAsString(result.get()),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void save() throws JsonProcessingException, JSONException {
    when(journeyJpaRepository.save(any())).thenReturn(journeyEntity);
    when(journeyEntityMapper.toJourney(any())).thenReturn(journey);
    Journey result = journeyRepository.save(journey);
    verify(journeyJpaRepository,times(1)).save(any());
    verify(journeyEntityMapper,times(1)).toJourney(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(journey),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void remove() {
    doNothing().when(journeyJpaRepository).delete(any());
    when(journeyEntityMapper.toJourney(any())).thenReturn(journey);
    journeyRepository.remove(journey);
    verify(journeyJpaRepository,times(1)).delete(any());
  }
}