package application.rest.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;

@RunWith(JUnit4.class)
public class AppApiImplTest {

  AppApiImpl appApi = new AppApiImpl();

  @Test
  public void getStatus() {
    assertEquals(ResponseEntity.ok().build(), appApi.getStatus());
  }

}