package domain.validator;

import domain.exception.BusinessValidationException;

public interface Validator<T> {

  void validate(T model) throws BusinessValidationException;

}
