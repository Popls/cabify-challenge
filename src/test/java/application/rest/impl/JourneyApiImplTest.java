package application.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import application.dto.CarListDto;
import application.dto.GroupDto;
import application.mapper.GroupDtoMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.model.Group;
import domain.model.Journey;
import domain.service.JourneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

@RunWith(JUnit4.class)
public class JourneyApiImplTest {

  private static final String DTO_PATH = "src/test/resources/infrastructure/dto/";

  private JourneyService journeyService = Mockito.mock(JourneyService.class);
  private GroupDtoMapper groupDtoMapper = Mockito.mock(GroupDtoMapper.class);
  private JourneyApiImpl journeyApi;
  private ObjectMapper objectMapper;

  public JourneyApiImplTest() {
    journeyApi = new JourneyApiImpl(journeyService,groupDtoMapper);
    objectMapper = new ObjectMapper();
  }

  @Test
  public void postJourneyAccepted() throws IOException {
    GroupDto groupDto = objectMapper
        .readValue(Files.readString(Path.of(DTO_PATH + "groupDto.json")), GroupDto.class);
    when(journeyService.create(any())).thenReturn(Journey.builder().build());
    when(groupDtoMapper.toGroup(any())).thenReturn(Group.builder().build());
    ResponseEntity<Void> responseEntity = journeyApi.postJourney(groupDto);
    verify(journeyService, times(1)).create(any());
    verify(groupDtoMapper, times(1)).toGroup(any());
    assertEquals(ResponseEntity.accepted().build(), responseEntity);
  }

  @Test
  public void postJourneyOk() throws IOException {
    GroupDto groupDto = objectMapper
        .readValue(Files.readString(Path.of(DTO_PATH + "groupDto.json")), GroupDto.class);
    when(journeyService.create(any())).thenReturn(null);
    when(groupDtoMapper.toGroup(any())).thenReturn(Group.builder().build());
    ResponseEntity<Void> responseEntity = journeyApi.postJourney(groupDto);
    verify(journeyService, times(1)).create(any());
    verify(groupDtoMapper, times(1)).toGroup(any());
    assertEquals(ResponseEntity.ok().build(), responseEntity);
  }

}