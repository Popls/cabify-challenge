package domain.validator.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.exception.BusinessValidationException;
import domain.model.Group;
import domain.model.Journey;
import org.junit.Test;
import org.mockito.Mockito;

public class GroupValidatorNotAssignedTest {

  private GroupValidatorImpl groupValidator = Mockito.mock(GroupValidatorImpl.class);
  private GroupValidatorNotAssigned groupValidatorNotAssigned;

  public GroupValidatorNotAssignedTest(){
    groupValidatorNotAssigned = new GroupValidatorNotAssigned(groupValidator);
  }

  @Test
  public void validate() throws BusinessValidationException {
    Group group = Group.builder().id(1).journey(Journey.builder().id(1).build()).build();
    doNothing().when(groupValidator).validate(any());
    groupValidatorNotAssigned.validate(group);
    verify(groupValidator,times(1)).validate(any());
  }

  @Test(expected = BusinessValidationException.class)
  public void validateNotAssignedException() throws BusinessValidationException {
    Group group = Group.builder().id(1).build();
    doNothing().when(groupValidator).validate(any());
    groupValidatorNotAssigned.validate(group);
  }

  @Test(expected = BusinessValidationException.class)
  public void validateValidatorException() throws BusinessValidationException {
    doThrow(new BusinessValidationException()).when(groupValidator).validate(any());
    groupValidatorNotAssigned.validate(null);
  }
}