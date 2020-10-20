package infrastructure.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Group;
import infrastructure.entity.GroupEntity;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GroupEntityMapperTest {

  private GroupEntityMapper groupEntityMapper;
  private GroupEntity groupEntity;
  private Group group;
  private ObjectMapper objectMapper;

  public GroupEntityMapperTest() {
    groupEntityMapper = new GroupEntityMapperImpl();
    groupEntity = GroupEntity.builder().id(1).people(4).build();
    group = Group.builder().id(1).people(4).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void toGroup() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(groupEntityMapper.toGroup(groupEntity)),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void toGroupEntity() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(groupEntity),
        objectMapper.writeValueAsString(groupEntityMapper.toGroupEntity(group)),
        JSONCompareMode.LENIENT);
  }
}