package infrastructure.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.model.Group;
import domain.model.Journey;
import infrastructure.entity.CarEntity;
import infrastructure.entity.GroupEntity;
import infrastructure.entity.JourneyEntity;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JourneyEntityMapperTest {

  private JourneyEntityMapper journeyEntityMapper;
  private ObjectMapper objectMapper;
  private Journey journey;
  private JourneyEntity journeyEntity;

  public JourneyEntityMapperTest(){
    journeyEntityMapper = new JourneyEntityMapperImpl();
    journey = Journey.builder().id(1).car(Car.builder().id(1).build())
        .group(Group.builder().id(1).build()).build();
    journeyEntity = JourneyEntity.builder().id(1).carEntity(CarEntity.builder().id(1).build())
        .groupEntity(GroupEntity.builder().id(1).build()).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void toJourney() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(journey),
        objectMapper.writeValueAsString(journeyEntityMapper.toJourney(journeyEntity)),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void toJourneyEntity() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(journeyEntity),
        objectMapper.writeValueAsString(journeyEntityMapper.toJourneyEntity(journey)),
        JSONCompareMode.LENIENT);
  }
}