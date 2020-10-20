package domain.service;

import javax.transaction.Transactional;

import domain.exception.ContentNotFoundException;
import domain.model.Group;
import domain.repository.GroupRepository;
import domain.repository.JourneyRepository;
import domain.validator.GroupValidatorDecorator;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class GroupService {

  private GroupRepository groupRepository;
  private JourneyRepository journeyRepository;
  private GroupValidatorDecorator groupValidator;

  @SneakyThrows
  public Group locate(Group group) {
    Group result = groupRepository.find(group).orElseThrow(ContentNotFoundException::new);
    journeyRepository.find(group).ifPresent(journey -> result.setJourney(journey));
    groupValidator.validate(result);
    return result;
  }

  @SneakyThrows
  public void remove(Group group) {
    groupRepository.find(group).orElseThrow(ContentNotFoundException::new);
    journeyRepository.find(group).ifPresent(journey -> journeyRepository.remove(journey));
    groupRepository.remove(group);
  }

}
