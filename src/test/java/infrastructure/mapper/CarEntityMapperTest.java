package infrastructure.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.model.Journey;
import infrastructure.entity.CarEntity;
import infrastructure.entity.JourneyEntity;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class CarEntityMapperTest {

  private CarEntityMapper carEntityMapper;
  private Car car;
  private CarEntity carEntity;
  private ObjectMapper objectMapper;

  public CarEntityMapperTest() {
    carEntityMapper = new CarEntityMapperImpl();
    car = Car.builder().id(1).seats(4).build();
    carEntity = CarEntity.builder().id(1).seats(4).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  public void toCar() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(car),
        objectMapper.writeValueAsString(carEntityMapper.toCar(carEntity)),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void toCarEntity() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(carEntity),
        objectMapper.writeValueAsString(carEntityMapper.toCarEntity(car)),
        JSONCompareMode.LENIENT);
  }
}