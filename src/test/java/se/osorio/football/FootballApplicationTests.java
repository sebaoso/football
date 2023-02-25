package se.osorio.football;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = { FootballApplication.class })
@ExtendWith(SpringExtension.class)
public class FootballApplicationTests {

  @Autowired
  private TeamRepository teamRepository;

  @Test
  public void testGetTeamsByName() {
    FootballRestController controller = new FootballRestController(teamRepository);
    List<Team> teams = controller.getTeamsByName("Argentina");
    assertEquals(1, teams.size());
  }
}
