package application.rest.impl;

import java.util.Optional;
import javax.validation.Valid;

import application.dto.GroupDto;
import application.mapper.GroupDtoMapper;
import application.rest.spec.JourneyApi;
import domain.service.JourneyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@Api(tags = {"Journey"})
public class JourneyApiImpl implements JourneyApi {

  @Autowired
  private JourneyService journeyService;
  @Autowired
  private GroupDtoMapper groupDtoMapper;

  @Override
  public ResponseEntity<Void> postJourney(@Valid GroupDto body) {
    if(Optional.ofNullable(journeyService.create(groupDtoMapper.toGroup(body))).isPresent()){
      return ResponseEntity.accepted().build();
    }
    return ResponseEntity.ok().build();
  }

}
