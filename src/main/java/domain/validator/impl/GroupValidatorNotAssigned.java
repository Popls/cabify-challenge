package domain.validator.impl;

import java.util.Optional;

import domain.exception.BusinessValidationException;
import domain.model.Group;
import domain.model.Journey;
import domain.validator.GroupValidatorDecorator;
import domain.validator.Validator;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GroupValidatorNotAssigned extends GroupValidatorDecorator {

  public GroupValidatorNotAssigned(Validator validator) {
    super(validator);
  }

  @Override
  public void validate(Group model) throws BusinessValidationException {
    super.validate(model);
    Optional<Journey> journey = Optional.ofNullable(model.getJourney());
    if (!journey.isPresent()) {
      log.error("{} is waiting to be assigned to a car.", model.toString());
      throw new BusinessValidationException();
    }
  }

}
