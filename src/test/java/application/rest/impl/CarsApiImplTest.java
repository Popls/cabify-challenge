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
import application.mapper.CarDtoMapper;
import application.rest.spec.CarsApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

@RunWith(JUnit4.class)
public class CarsApiImplTest {

  private static final String DTO_PATH = "src/test/resources/infrastructure/dto/";

  private CarService carService = Mockito.mock(CarService.class);
  private CarDtoMapper carDtoMapper = Mockito.mock(CarDtoMapper.class);
  private CarsApiImpl carsApi;
  private ObjectMapper objectMapper;

  public CarsApiImplTest() {
    carsApi = new CarsApiImpl(carService, carDtoMapper);
    objectMapper = new ObjectMapper();
  }

  @Test
  public void putCars() throws IOException {
    CarListDto carListDto = objectMapper
        .readValue(Files.readString(Path.of(DTO_PATH + "carListDto.json")), CarListDto.class);
    doNothing().when(carService).updateAll(any());
    when(carDtoMapper.toCar(any())).thenReturn(Car.builder().build());
    ResponseEntity<Void> responseEntity = carsApi.putCars(carListDto);
    verify(carService, times(1)).updateAll(any());
    assertEquals(ResponseEntity.ok().build(), responseEntity);
  }

}