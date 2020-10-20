package domain.service;

import java.util.List;
import javax.transaction.Transactional;

import domain.model.Car;
import domain.repository.CarRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CarService {

  private CarRepository carRepository;

  @Transactional
  public void updateAll(List<Car> cars) {
    carRepository.removeAll();
    carRepository.saveAll(cars);
  }

}
