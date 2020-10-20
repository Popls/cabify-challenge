package application.rest.impl;

import application.dto.CarDto;
import application.mapper.CarDtoMapper;
import application.rest.spec.DropoffApi;
import application.rest.spec.LocateApi;
import domain.model.Group;
import domain.service.GroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@Api(tags = {"Group"})
public class GroupApiImpl implements LocateApi, DropoffApi {

  @Autowired
  private GroupService groupService;
  @Autowired
  private CarDtoMapper carDtoMapper;

  @Override
  public ResponseEntity<CarDto> postLocate(Integer ID) {
    return ResponseEntity.accepted().body(
        carDtoMapper.toCarDto(groupService.locate(Group.builder().id(ID).build()))
    );
  }

  @Override
  public ResponseEntity<Void> postDropoff(Integer ID) {
    groupService.remove(Group.builder().id(ID).build());
    return ResponseEntity.noContent().build();
  }
}
