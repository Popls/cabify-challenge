package domain.validator.impl;

import java.util.Objects;

import domain.exception.BusinessValidationException;
import domain.model.Group;
import domain.validator.Validator;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GroupValidatorImpl implements Validator<Group> {

  @Override
  public void validate(Group model) throws BusinessValidationException {
    if (Objects.isNull(model)) {
      throw new BusinessValidationException();
    }
  }

}
