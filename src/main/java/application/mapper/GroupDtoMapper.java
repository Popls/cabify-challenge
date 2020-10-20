package application.mapper;

import application.dto.GroupDto;
import domain.model.Group;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Mapper(componentModel = "spring")
@Log4j2
public abstract class GroupDtoMapper {

  public abstract Group toGroup(GroupDto groupDto);

}
