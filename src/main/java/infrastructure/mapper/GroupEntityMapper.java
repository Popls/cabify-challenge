package infrastructure.mapper;

import domain.model.Group;
import infrastructure.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Mapper(componentModel = "spring")
@Log4j2
public abstract class GroupEntityMapper {

  public abstract Group toGroup(GroupEntity groupEntity);

  public abstract GroupEntity toGroupEntity(Group group);

}
