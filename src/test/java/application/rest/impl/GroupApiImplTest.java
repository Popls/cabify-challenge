package application.rest.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import application.dto.CarDto;
import application.mapper.CarDtoMapper;
import domain.model.Group;
import domain.service.GroupService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

@RunWith(JUnit4.class)
public class GroupApiImplTest {

  private GroupService groupService = Mockito.mock(GroupService.class);
  private CarDtoMapper carDtoMapper = Mockito.mock(CarDtoMapper.class);
  private GroupApiImpl groupApi;

  public GroupApiImplTest() {
    groupApi = new GroupApiImpl(groupService, carDtoMapper);
  }

  @Test
  public void postLocate() {
    CarDto carDtoExpected = new CarDto();
    carDtoExpected.setId(1);
    carDtoExpected.setSeats(4);
    when(groupService.locate(any())).thenReturn(Group.builder().build());
    when(carDtoMapper.toCarDto(any())).thenReturn(carDtoExpected);
    ResponseEntity<CarDto> responseEntity = groupApi.postLocate(1);
    verify(groupService,times(1)).locate(any());
    verify(carDtoMapper,times(1)).toCarDto(any());
    assertEquals(ResponseEntity.accepted().body(carDtoExpected), responseEntity);
  }

  @Test
  public void postDropoff() {
    doNothing().when(groupService).remove(any());
    ResponseEntity<Void> responseEntity = groupApi.postDropoff(1);
    verify(groupService,times(1)).remove(any());
    assertEquals(ResponseEntity.noContent().build(), responseEntity);
  }

}