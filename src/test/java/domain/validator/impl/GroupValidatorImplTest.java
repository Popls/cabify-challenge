package domain.validator.impl;

import static org.junit.Assert.*;

import domain.exception.BusinessValidationException;
import domain.model.Group;
import org.junit.Test;

public class GroupValidatorImplTest {

  private GroupValidatorImpl groupValidator;

  public GroupValidatorImplTest(){
    groupValidator = new GroupValidatorImpl();
  }

  @Test
  public void validate() throws BusinessValidationException {
    Group group = Group.builder().build();
    groupValidator.validate(group);
  }

  @Test(expected = BusinessValidationException.class)
  public void validateThrow() throws BusinessValidationException {
    groupValidator.validate(null);
  }
}