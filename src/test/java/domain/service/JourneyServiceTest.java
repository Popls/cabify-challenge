package domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.model.Group;
import domain.model.Journey;
import domain.repository.CarRepository;
import domain.repository.GroupRepository;
import domain.repository.JourneyRepository;
import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JourneyServiceTest {

  private CarRepository carRepository = Mockito.mock(CarRepository.class);
  private JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);
  private GroupRepository groupRepository = Mockito.mock(GroupRepository.class);
  private ObjectMapper objectMapper;
  private JourneyService journeyService;
  private Group group;

  public JourneyServiceTest() {
    journeyService = new JourneyService(carRepository, journeyRepository, groupRepository);
    objectMapper = new ObjectMapper();
    group = Group.builder().id(1).people(4).build();
  }

  @Test
  public void create() throws JsonProcessingException, JSONException {
    Journey expected = Journey.builder().id(1).build();
    group = Group.builder().id(1).people(4).build();
    when(journeyRepository.find(any())).thenReturn(Optional.of(Journey.builder().id(1).build()));
    when(carRepository.findAllAvailableCarsBySeats(any()))
        .thenReturn(List.of(Car.builder().id(1).seats(4).build()));
    when(groupRepository.save(any()))
        .thenReturn(group);
    when(journeyRepository.save(any())).thenReturn(expected);
    doNothing().when(journeyRepository).remove(any());
    doNothing().when(groupRepository).remove(any());
    Journey result = journeyService.create(group);
    verify(journeyRepository,times(1)).find(any());
    verify(journeyRepository,times(1)).remove(any());
    verify(groupRepository,times(1)).remove(any());
    verify(groupRepository,times(1)).save(any());
    verify(carRepository,times(1)).findAllAvailableCarsBySeats(any());
    verify(journeyRepository,times(1)).save(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(expected),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void createWithOutJourney() throws JsonProcessingException, JSONException {
    Journey expected = Journey.builder().id(1).build();
    group = Group.builder().id(1).people(4).build();
    when(journeyRepository.find(any())).thenReturn(Optional.ofNullable(null));
    when(carRepository.findAllAvailableCarsBySeats(any()))
        .thenReturn(List.of(Car.builder().id(1).seats(4).build()));
    when(groupRepository.save(any()))
        .thenReturn(group);
    when(journeyRepository.save(any())).thenReturn(expected);
    Journey result = journeyService.create(group);
    verify(journeyRepository,times(1)).find(any());
    verify(journeyRepository,times(0)).remove(any());
    verify(groupRepository,times(0)).remove(any());
    verify(groupRepository,times(1)).save(any());
    verify(carRepository,times(1)).findAllAvailableCarsBySeats(any());
    verify(journeyRepository,times(1)).save(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(expected),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void createEmptyAvailableCar() {
    group = Group.builder().id(1).people(4).build();
    when(journeyRepository.find(any())).thenReturn(Optional.of(Journey.builder().id(1).build()));
    when(carRepository.findAllAvailableCarsBySeats(any()))
        .thenReturn(List.of());
    when(groupRepository.save(any()))
        .thenReturn(group);
    when(journeyRepository.save(any())).thenReturn(Journey.builder().id(1).build());
    doNothing().when(journeyRepository).remove(any());
    doNothing().when(groupRepository).remove(any());
    Journey result = journeyService.create(group);
    verify(journeyRepository,times(1)).find(any());
    verify(journeyRepository,times(1)).remove(any());
    verify(groupRepository,times(1)).remove(any());
    verify(groupRepository,times(1)).save(any());
    verify(carRepository,times(1)).findAllAvailableCarsBySeats(any());
    verify(journeyRepository,times(0)).save(any());
    assertNull(result);
  }

}