package domain.exception;

public class BusinessValidationException extends Exception {

  public static final String BUSINESS_VALIDATION_EXCEPTION = "Model hasn't satisfied validations.";

  public BusinessValidationException(){
    super(BUSINESS_VALIDATION_EXCEPTION);
  }

}
