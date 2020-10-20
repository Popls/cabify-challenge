package infrastructure.mapper;

import domain.model.Car;
import infrastructure.entity.CarEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Mapper(componentModel = "spring")
@Log4j2
public abstract class CarEntityMapper {

  public abstract Car toCar(CarEntity carEntity);

  public abstract CarEntity toCarEntity(Car car);

}
