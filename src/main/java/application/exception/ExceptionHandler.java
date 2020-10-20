package application.exception;

import domain.exception.ContentNotFoundException;
import domain.exception.BusinessValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @org.springframework.web.bind.annotation.ExceptionHandler(value = ContentNotFoundException.class)
  public ResponseEntity<Void> handleContentNotFoundException(ContentNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ResponseBody
  @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessValidationException.class)
  public ResponseEntity<Void> handleBusinessValidationException(
      BusinessValidationException exception) {
    return ResponseEntity.noContent().build();
  }

  @ResponseBody
  @org.springframework.web.bind.annotation.ExceptionHandler(value = MandatoryFieldNotFoundException.class)
  public ResponseEntity<Void> handleMandatoryFieldNotFoundException(
      MandatoryFieldNotFoundException exception) {
    return ResponseEntity.badRequest().build();
  }

}
