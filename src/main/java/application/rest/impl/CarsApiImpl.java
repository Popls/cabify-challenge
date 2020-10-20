package application.rest.impl;

import java.util.stream.Collectors;
import javax.validation.Valid;

import application.dto.CarListDto;
import application.mapper.CarDtoMapper;
import application.rest.spec.CarsApi;
import domain.service.CarService;
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
@Api(tags = {"Cars"})
public class CarsApiImpl implements CarsApi {

  @Autowired
  private CarService carService;
  @Autowired
  private CarDtoMapper carDtoMapper;

  @Override
  public ResponseEntity<Void> putCars(@Valid CarListDto body) {
    carService.updateAll(
        body.stream()
            .map(carDto -> carDtoMapper.toCar(carDto))
            .collect(Collectors.toList())
    );
    return ResponseEntity.ok().build();
  }

}
