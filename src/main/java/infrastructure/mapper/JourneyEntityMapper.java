package infrastructure.mapper;

import domain.model.Journey;
import infrastructure.entity.JourneyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Mapper(componentModel = "spring")
@Log4j2
public abstract class JourneyEntityMapper {

  @Mapping(source = "carEntity", target = "car")
  @Mapping(source = "groupEntity", target = "group")
  public abstract Journey toJourney(JourneyEntity journeyEntity);

  @Mapping(source = "car", target = "carEntity")
  @Mapping(source = "group", target = "groupEntity")
  public abstract JourneyEntity toJourneyEntity(Journey journey);

}
