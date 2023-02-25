package se.osorio.football.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Schema(description = "Team")
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Integer position;

  @Column(name="nr_of_cups")
  private Integer nrOfCups;

  public Team(Integer id, String name, Integer position, Integer nrOfCups) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.nrOfCups = nrOfCups;
  }

  public Team() {

  }
}
