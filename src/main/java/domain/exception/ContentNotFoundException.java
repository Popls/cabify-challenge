package domain.exception;

public class ContentNotFoundException extends Exception {

  public static final String CONTENT_NOT_FOUND = "Repository hasn't found any content.";

  public ContentNotFoundException(){
    super(CONTENT_NOT_FOUND);
  }

}
