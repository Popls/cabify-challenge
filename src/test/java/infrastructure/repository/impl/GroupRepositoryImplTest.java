package infrastructure.repository.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Group;
import infrastructure.entity.GroupEntity;
import infrastructure.mapper.GroupEntityMapper;
import infrastructure.repository.GroupJpaRepository;
import org.json.JSONException;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GroupRepositoryImplTest {

  private GroupJpaRepository groupJpaRepository = Mockito.mock(GroupJpaRepository.class);
  private GroupEntityMapper groupEntityMapper = Mockito.mock(GroupEntityMapper.class);
  private GroupRepositoryImpl groupRepository;
  private GroupEntity groupEntity;
  private Group group;
  private ObjectMapper objectMapper;

  public GroupRepositoryImplTest() {
    groupRepository = new GroupRepositoryImpl(groupJpaRepository, groupEntityMapper);
    group = Group.builder().id(1).people(4).build();
    groupEntity = GroupEntity.builder().id(1).people(4).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void find() throws JsonProcessingException, JSONException {
    when(groupJpaRepository.findById(any())).thenReturn(Optional.of(groupEntity));
    when(groupEntityMapper.toGroup(any())).thenReturn(group);
    Optional<Group> result = groupRepository.find(group);
    verify(groupJpaRepository,times(1)).findById(any());
    verify(groupEntityMapper,times(1)).toGroup(any());
    assertTrue(result.isPresent());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(result.get()),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void save() throws JsonProcessingException, JSONException {
    when(groupJpaRepository.save(any())).thenReturn(groupEntity);
    when(groupEntityMapper.toGroup(any())).thenReturn(group);
    Group result = groupRepository.save(group);
    verify(groupJpaRepository,times(1)).save(any());
    verify(groupEntityMapper,times(1)).toGroup(any());
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(result),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void remove() {
    doNothing().when(groupJpaRepository).delete(any());
    when(groupEntityMapper.toGroupEntity(any())).thenReturn(groupEntity);
    groupRepository.remove(group);
    verify(groupEntityMapper,times(1)).toGroupEntity(any());
    verify(groupJpaRepository,times(1)).delete(any());
  }
}