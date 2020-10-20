package infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "group_entity")
public class GroupEntity {

  @Id
  private Integer id;
  private Integer people;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinTable(name = "journey_entity",
      joinColumns =
          {@JoinColumn(name = "car_entity_id", referencedColumnName = "id")},
      inverseJoinColumns =
          {@JoinColumn(name = "group_entity_id", referencedColumnName = "id")})
  private JourneyEntity journeyEntity;

}
