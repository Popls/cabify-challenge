package domain.service;

import java.util.List;

import domain.model.Car;
import domain.model.Group;
import domain.model.Journey;
import domain.repository.CarRepository;
import domain.repository.GroupRepository;
import domain.repository.JourneyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JourneyService {

  private CarRepository carRepository;
  private JourneyRepository journeyRepository;
  private GroupRepository groupRepository;

  public Journey create(Group group) {
    journeyRepository.find(group).ifPresent(journey -> {
      journeyRepository.remove(journey);
      groupRepository.remove(group);
    });
    groupRepository.save(group);
    List<Car> availableCars = carRepository.findAllAvailableCarsBySeats(group.getPeople());
    //TODO send group to a queue if there aren't any car available
    return availableCars.stream().findFirst()
        .map(car -> journeyRepository.save(Journey.builder().group(group).car(car).build()))
        .orElse(null);
  }

}
