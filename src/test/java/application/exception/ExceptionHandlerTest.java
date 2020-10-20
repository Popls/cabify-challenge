package application.exception;

import static org.junit.Assert.assertEquals;

import domain.exception.BusinessValidationException;
import domain.exception.ContentNotFoundException;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ExceptionHandlerTest {

  private ExceptionHandler exceptionHandler;

  public ExceptionHandlerTest() {
    exceptionHandler = new ExceptionHandler();
  }

  @Test
  public void handleContentNotFoundException() {
    assertEquals(ResponseEntity.notFound().build(),
        exceptionHandler.handleContentNotFoundException(new ContentNotFoundException()));
  }

  @Test
  public void handleBusinessValidationException() {
    assertEquals(ResponseEntity.noContent().build(),
        exceptionHandler.handleBusinessValidationException(new BusinessValidationException()));
  }

  @Test
  public void handleMandatoryFieldNotFoundException() {
    assertEquals(ResponseEntity.badRequest().build(),
        exceptionHandler
            .handleMandatoryFieldNotFoundException(new MandatoryFieldNotFoundException()));
  }
}