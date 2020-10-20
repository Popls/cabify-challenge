package domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.exception.BusinessValidationException;
import domain.exception.ContentNotFoundException;
import domain.model.Group;
import domain.model.Journey;
import domain.repository.GroupRepository;
import domain.repository.JourneyRepository;
import domain.validator.GroupValidatorDecorator;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GroupServiceTest {

  private GroupRepository groupRepository = Mockito.mock(GroupRepository.class);
  private JourneyRepository journeyRepository = Mockito.mock(JourneyRepository.class);
  private GroupValidatorDecorator groupValidator = Mockito.mock(GroupValidatorDecorator.class);
  private GroupService groupService;
  private ObjectMapper objectMapper;
  private Group group;

  public GroupServiceTest() {
    groupService = new GroupService(groupRepository, journeyRepository, groupValidator);
    objectMapper = new ObjectMapper();
  }

  @Before
  public void setUp(){
    group = Group.builder().id(1).people(4).journey(Journey.builder().id(1).build()).build();
  }

  @Test
  public void locate() throws JsonProcessingException, JSONException, BusinessValidationException {
    Journey journey = Journey.builder().id(1).build();
    when(groupRepository.find(any())).thenReturn(Optional.of(group));
    when(journeyRepository.find(any())).thenReturn(Optional.of(journey));
    doNothing().when(groupValidator).validate(any());
    Group result = groupService.locate(group);
    verify(groupRepository, times(1)).find(any());
    verify(journeyRepository, times(1)).find(any());
    verify(groupValidator, times(1)).validate(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test(expected = ContentNotFoundException.class)
  public void locateWithException() throws JsonProcessingException, JSONException, BusinessValidationException {
    when(groupRepository.find(any())).thenReturn(Optional.ofNullable(null));
    Group result = groupService.locate(group);
  }

  @Test
  public void locateWithoutJourney() throws JsonProcessingException, JSONException, BusinessValidationException {
    when(groupRepository.find(any())).thenReturn(Optional.of(group));
    when(journeyRepository.find(any())).thenReturn(Optional.ofNullable(null));
    doNothing().when(groupValidator).validate(any());
    Group result = groupService.locate(group);
    verify(groupRepository, times(1)).find(any());
    verify(journeyRepository, times(1)).find(any());
    verify(groupValidator, times(1)).validate(any());
    group.setJourney(null);
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test(expected = BusinessValidationException.class)
  public void locateNotValid() throws JsonProcessingException, JSONException, BusinessValidationException {
    when(groupRepository.find(any())).thenReturn(Optional.of(group));
    when(journeyRepository.find(any())).thenReturn(Optional.ofNullable(null));
    doThrow(new BusinessValidationException()).when(groupValidator).validate(any());
    Group result = groupService.locate(group);
  }

  @Test
  public void remove() {
    when(groupRepository.find(any())).thenReturn(Optional.of(group));
    when(journeyRepository.find(any())).thenReturn(Optional.ofNullable(Journey.builder().id(1).build()));
    doNothing().when(journeyRepository).remove(any());
    doNothing().when(groupRepository).remove(any());
    groupService.remove(group);
    verify(groupRepository, times(1)).find(any());
    verify(journeyRepository, times(1)).find(any());
    verify(groupRepository, times(1)).remove(any());
    verify(journeyRepository, times(1)).remove(any());
  }

  @Test(expected = ContentNotFoundException.class)
  public void removeWithException() {
    when(groupRepository.find(any())).thenReturn(Optional.ofNullable(null));
    groupService.remove(group);
  }

  @Test
  public void removeWithoutJourney() {
    when(groupRepository.find(any())).thenReturn(Optional.of(group));
    when(journeyRepository.find(any())).thenReturn(Optional.ofNullable(null));
    doNothing().when(journeyRepository).remove(any());
    doNothing().when(groupRepository).remove(any());
    groupService.remove(group);
    verify(groupRepository, times(1)).find(any());
    verify(journeyRepository, times(1)).find(any());
    verify(groupRepository, times(1)).remove(any());
    verify(journeyRepository, times(0)).remove(any());
  }

}