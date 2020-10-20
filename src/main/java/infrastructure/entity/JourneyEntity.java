package infrastructure.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "journey_entity")
public class JourneyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;
  @OneToOne
  private CarEntity carEntity;
  @OneToOne
  private GroupEntity groupEntity;

}
