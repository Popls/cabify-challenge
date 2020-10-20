package application.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import application.dto.CarDto;
import application.exception.MandatoryFieldNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.model.Car;
import domain.model.Group;
import domain.model.Journey;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

@RunWith(JUnit4.class)
public class CarDtoMapperTest {

  private static final String DTO_PATH = "src/test/resources/infrastructure/dto/";

  private Car car;
  private Group group;
  private CarDto carDto;
  private CarDtoMapper carDtoMapper;
  private ObjectMapper objectMapper;

  public CarDtoMapperTest() throws IOException {
    carDtoMapper = new CarDtoMapperImpl();
    objectMapper = new ObjectMapper();
    car = Car.builder().id(1).seats(4).build();
    group = Group.builder().id(1).people(4).journey(Journey.builder().id(1).car(car).build())
        .build();
    carDto = objectMapper
        .readValue(Files.readString(Path.of(DTO_PATH + "carDto.json")), CarDto.class);
  }

  @Test
  public void toCar() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(car),
        objectMapper.writeValueAsString(carDtoMapper.toCar(carDto)),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void toCarDto() throws JsonProcessingException, JSONException {
    JSONAssert.assertEquals(objectMapper.writeValueAsString(carDto),
        objectMapper.writeValueAsString(carDtoMapper.toCarDto(group)),
        JSONCompareMode.LENIENT);
  }

  @Test
  public void isNullThrow() throws MandatoryFieldNotFoundException {
    assertTrue(carDtoMapper.isNullThrow(1).equals(1));
  }

  @Test(expected = MandatoryFieldNotFoundException.class)
  public void isNullThrowOfNull() throws MandatoryFieldNotFoundException {
    carDtoMapper.isNullThrow(null);
  }

}