package se.osorio.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Team")
public class TeamEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private Integer position;

  @Column(name="nr_of_cups")
  private Integer nrOfCups;

  @OneToMany(fetch = EAGER, mappedBy = "team")
  private List<PlayerEntity> players = new ArrayList<>();

  public void addPlayer(PlayerEntity player){
    players.add(player);
  }
  public void addPlayers(List<PlayerEntity> players){
    players.stream().forEach(this::addPlayer);
  }

}
