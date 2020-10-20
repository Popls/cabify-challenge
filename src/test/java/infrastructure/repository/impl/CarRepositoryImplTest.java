package infrastructure.repository.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import infrastructure.entity.CarEntity;
import infrastructure.mapper.CarEntityMapper;
import infrastructure.repository.CarJpaRepository;
import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

public class CarRepositoryImplTest {

  private CarJpaRepository carJpaRepository = Mockito.mock(CarJpaRepository.class);
  private CarEntityMapper carEntityMapper = Mockito.mock(CarEntityMapper.class);
  private CarRepositoryImpl carRepository;
  private CarEntity carEntity;
  private Car car;
  private ObjectMapper objectMapper;

  public CarRepositoryImplTest(){
    carEntity = CarEntity.builder().id(1).seats(4).build();
    car = Car.builder().id(1).seats(4).build();
    carRepository = new CarRepositoryImpl(carJpaRepository,carEntityMapper);
    objectMapper = new ObjectMapper();
  }

  @Test
  public void findAllAvailableCarsBySeats() throws JsonProcessingException, JSONException {
    when(carJpaRepository.findBySeatsGreaterThanEqual(any())).thenReturn(List.of(carEntity));
    when(carEntityMapper.toCar(any())).thenReturn(car);
    List<Car> cars = carRepository.findAllAvailableCarsBySeats(1);
    verify(carJpaRepository,times(1)).findBySeatsGreaterThanEqual(any());
    verify(carEntityMapper,times(1)).toCar(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(List.of(car)),
        objectMapper.writeValueAsString(cars),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void saveAll() throws JsonProcessingException, JSONException {
    when(carJpaRepository.saveAll(any())).thenReturn(List.of(carEntity));
    when(carEntityMapper.toCarEntity(any())).thenReturn(carEntity);
    when(carEntityMapper.toCar(any())).thenReturn(car);
    List<Car> cars = carRepository.saveAll(List.of(car));
    verify(carJpaRepository,times(1)).saveAll(any());
    verify(carEntityMapper,times(1)).toCar(any());
    verify(carEntityMapper,times(1)).toCarEntity(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(List.of(car)),
        objectMapper.writeValueAsString(cars),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void removeAll() {
    doNothing().when(carJpaRepository).deleteAll();
    carRepository.removeAll();
    verify(carJpaRepository, times(1)).deleteAll();
  }
}