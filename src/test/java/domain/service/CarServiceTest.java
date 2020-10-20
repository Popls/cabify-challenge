package domain.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import domain.model.Car;
import domain.repository.CarRepository;
import org.junit.Test;
import org.mockito.Mockito;

public class CarServiceTest {

  private CarRepository carRepository = Mockito.mock(CarRepository.class);
  private CarService carService;

  public CarServiceTest(){
    carService = new CarService(carRepository);
  }

  @Test
  public void updateAll() {
    List<Car> carList = List.of(Car.builder().id(1).seats(4).build());
    when(carRepository.saveAll(any())).thenReturn(carList);
    doNothing().when(carRepository).removeAll();
    carService.updateAll(carList);
    verify(carRepository,times(1)).removeAll();
    verify(carRepository,times(1)).saveAll(any());
  }

}