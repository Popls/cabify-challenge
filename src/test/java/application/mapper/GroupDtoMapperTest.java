package application.mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import application.dto.GroupDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Group;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class GroupDtoMapperTest {

  private static final String DTO_PATH = "src/test/resources/infrastructure/dto/";

  private GroupDtoMapper groupDtoMapper;
  private ObjectMapper objectMapper;
  private GroupDto groupDto;
  private Group group;

  public GroupDtoMapperTest() throws IOException {
    groupDtoMapper = new GroupDtoMapperImpl();
    objectMapper = new ObjectMapper();
    groupDto = objectMapper
        .readValue(Files.readString(Path.of(DTO_PATH + "groupDto.json")), GroupDto.class);
    group = Group.builder().id(1).people(4).build();
  }

  @Test
  public void toGroup() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(group),
        objectMapper.writeValueAsString(groupDtoMapper.toGroup(groupDto)),
        JSONCompareMode.LENIENT);
  }
}