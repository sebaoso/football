package se.osorio.football;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.osorio.football.controller.FootballRestController;
import se.osorio.football.entity.PlayerEntity;
import se.osorio.football.entity.TeamEntity;
import se.osorio.football.model.Player;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;
import se.osorio.football.service.PlayerService;
import se.osorio.football.service.TeamService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class FootballApplicationTests {

  @MockBean
  private TeamService teamServiceMock;

  @Mock
  private TeamRepository teamRepository;

  @Autowired
  private PlayerService playerService;

  private TeamService teamService;

  @Autowired
  private MockMvc mvc;

  private static final String ARGENTINA = "Argentina";

  @BeforeEach
  public void initialiseMocks(){
    MockitoAnnotations.openMocks(this);
    teamService = new TeamService(teamRepository);
  }

  @Test
  public void testGetTeamsByName() {
    FootballRestController controller = new FootballRestController(teamService, playerService);

    List<PlayerEntity> playerEntityList = Arrays.asList(PlayerEntity.builder()
                    .name("Lionel Messi")
                    .club("PSG")
                    .position("RW")
                    .build(),
            PlayerEntity.builder()
                    .name("Angel Di Maria")
                    .club("Juventus")
                    .position("LW")
                    .build());

    List<TeamEntity> teamList = Arrays.asList(TeamEntity.builder()
            .name(ARGENTINA)
            .position(1)
            .nrOfCups(3)
            .players(playerEntityList)
            .build());

    when(teamRepository.findAllByNameContaining(ARGENTINA)).thenReturn(teamList);

    List<Team> teams = controller.getTeamsByName(ARGENTINA);
    assertEquals(1, teams.size());
  }

  @Test
  public void givenStartpage_shouldExistTitle() throws Exception {

    mvc.perform(MockMvcRequestBuilders.get("/football"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("FIFA World Cup 2022")));
  }

  @Test
  public void givenCountryArgentina_shouldExistMessi() throws Exception {

    Team team = Team.builder().name(ARGENTINA).position(1).nrOfCups(3).players(new ArrayList<>()).build();

    List<Player> playerList = Arrays.asList(Player.builder().name("Lionel Messi").club("PSG").position("RW").team(ARGENTINA).build(),
            Player.builder().name("Angel Di Maria").club("Juventus").position("LW").team(ARGENTINA).build());

    team.addPlayers(playerList);

    when(teamServiceMock.getTeam(1)).thenReturn(team);

    mvc.perform(MockMvcRequestBuilders.get("/playersofteam?id=1"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Lionel Messi")));
  }


}
