package infrastructure.repository.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.model.Car;
import domain.repository.CarRepository;
import infrastructure.entity.CarEntity;
import infrastructure.mapper.CarEntityMapper;
import infrastructure.repository.CarJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class CarRepositoryImpl implements CarRepository {

  @Autowired
  private CarJpaRepository carJpaRepository;
  @Autowired
  private CarEntityMapper carEntityMapper;

  @Override
  public List<Car> findAllAvailableCarsBySeats(Integer seats) {
    return carJpaRepository.findBySeatsGreaterThanEqual(seats).stream()
        .filter(carEntity -> Objects.isNull(carEntity.getJourneyEntity()))
        .map(carEntity -> carEntityMapper.toCar(carEntity))
        .collect(Collectors.toList());
  }

  @Override
  public List<Car> saveAll(List<Car> cars) {
    return ((List<CarEntity>) carJpaRepository
        .saveAll(
            cars.stream()
                .map(car -> carEntityMapper.toCarEntity(car))
                .collect(Collectors.toList())
        )).stream()
        .map(carEntity -> carEntityMapper.toCar(carEntity))
        .collect(Collectors.toList());
  }

  @Override
  public void removeAll() {
    carJpaRepository.deleteAll();
  }
}
