package application.exception;

public class MandatoryFieldNotFoundException extends Exception {

  public static final String FIELD_NOT_FOUND = "A mandatory field not found.";

  public MandatoryFieldNotFoundException(){
    super(FIELD_NOT_FOUND);
  }

}
