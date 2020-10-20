package domain.repository;

import java.util.List;

import domain.model.Car;

public interface CarRepository {

  List<Car> findAllAvailableCarsBySeats(Integer seats);

  List<Car> saveAll(List<Car> cars);

  void removeAll();

}
