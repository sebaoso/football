package se.osorio.football.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Schema(description = "Team")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

  private Integer id;
  private String name;
  private Integer position;
  private Integer nrOfCups;
  @ArraySchema(schema = @Schema(description = "List of players"))
  private List<Player> players = new ArrayList<>();
  public void addPlayer(Player player){
    players.add(player);
  }

  public void addPlayers(List<Player> players){
    players.stream().forEach(this::addPlayer);
  }

}
