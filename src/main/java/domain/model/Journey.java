package domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Journey {

  private Integer id;
  private Car car;
  private Group group;

}
