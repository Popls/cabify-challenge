package application.rest.impl;

import application.rest.spec.StatusApi;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@Api(tags = {"App"})
public class AppApiImpl implements StatusApi {

  @Override
  public ResponseEntity<Void> getStatus() {
    return  ResponseEntity.ok().build();
  }
}
