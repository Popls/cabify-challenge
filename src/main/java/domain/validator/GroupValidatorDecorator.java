package domain.validator;

import domain.exception.BusinessValidationException;
import domain.model.Group;

public abstract class GroupValidatorDecorator implements Validator<Group> {

  private Validator validator;

  public GroupValidatorDecorator(Validator<Group> validator){
    this.validator = validator;
  }

  @Override
  public void validate(Group model) throws BusinessValidationException {
    validator.validate(model);
  }

}
