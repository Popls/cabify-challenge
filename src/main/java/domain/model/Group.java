package domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Group {

  private Integer id;
  private Integer people;
  private Journey journey;

}
