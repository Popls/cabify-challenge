package domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Car {

  private Integer id;
  private Integer seats;
  private Journey journey;

}
