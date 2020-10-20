package infrastructure.repository.impl;

import java.util.Optional;

import domain.model.Group;
import domain.repository.GroupRepository;
import infrastructure.mapper.GroupEntityMapper;
import infrastructure.repository.GroupJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class GroupRepositoryImpl implements GroupRepository {

  @Autowired
  private GroupJpaRepository groupJpaRepository;
  @Autowired
  private GroupEntityMapper groupEntityMapper;

  @Override
  public Optional<Group> find(Group group) {
    return groupJpaRepository.findById(group.getId())
        .map(groupEntity -> groupEntityMapper.toGroup(groupEntity));
  }

  @Override
  public Group save(Group group) {
    return groupEntityMapper
        .toGroup(groupJpaRepository.save(groupEntityMapper.toGroupEntity(group)));
  }

  @Override
  public void remove(Group group) {
    groupJpaRepository.delete(groupEntityMapper.toGroupEntity(group));
  }
}
