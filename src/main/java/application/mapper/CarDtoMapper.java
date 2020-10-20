package application.mapper;

import java.util.Optional;

import application.dto.CarDto;
import application.exception.MandatoryFieldNotFoundException;
import domain.model.Car;
import domain.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Mapper(componentModel = "spring")
@Log4j2
public abstract class CarDtoMapper {

  @Mapping(target = "id", source = "id", qualifiedByName = "isNullThrow")
  @Mapping(target = "seats", source = "seats", qualifiedByName = "isNullThrow")
  public abstract Car toCar(CarDto carDto);

  @Mapping(target = "id", source = "journey.car.id")
  @Mapping(target = "seats", source = "journey.car.seats")
  public abstract CarDto toCarDto(Group group);

  @Named("isNullThrow")
  public Integer isNullThrow(Integer value) throws MandatoryFieldNotFoundException {
    return Optional.ofNullable(value).orElseThrow(MandatoryFieldNotFoundException::new);
  }

}
