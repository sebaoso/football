package se.osorio.football;

import lombok.extern.slf4j.Slf4j;
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
import se.osorio.football.service.TeamServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
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
  private static final String FRANCE = "France";

  @BeforeEach
  public void initialiseMocks(){
    MockitoAnnotations.openMocks(this);
    teamService = new TeamServiceImpl(teamRepository);
  }

  @Test
  public void testGetTeamsByName() {
    FootballRestController controller = new FootballRestController(teamService, playerService);

    List<TeamEntity> teamList = Arrays.asList(buildTeamEntity());

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

    Team team = buildTeam();

    when(teamServiceMock.getTeam(1)).thenReturn(team);

    mvc.perform(MockMvcRequestBuilders.get("/playersofteam?id=1"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Lionel Messi")));
  }



  private static Team buildTeam() {
    Team team = Team.builder().id(1).name(ARGENTINA).position(1).nrOfCups(3).players(new ArrayList<>()).build();

    List<Player> playerList = Arrays.asList(Player.builder().name("Lionel Messi").club("PSG").position("RW").team(ARGENTINA).build(),
            Player.builder().name("Angel Di Maria").club("Juventus").position("LW").team(ARGENTINA).build());

    team.addPlayers(playerList);
    return team;
  }

  private static TeamEntity buildTeamEntity() {

    List<PlayerEntity> argentinaPlayerEntityList = Arrays.asList(PlayerEntity.builder()
                    .name("Lionel Messi")
                    .club("PSG")
                    .position("RW")
                    .build(),
            PlayerEntity.builder()
                    .name("Angel Di Maria")
                    .club("Juventus")
                    .position("LW")
                    .build());
    TeamEntity teamEntity = TeamEntity.builder()
            .name(ARGENTINA)
            .position(1)
            .nrOfCups(3)
            .players(argentinaPlayerEntityList)
            .build();
    return teamEntity;

  }

  private static List<TeamEntity> buildTeamEntities() {
    List<PlayerEntity> argentinaPlayerEntityList = Arrays.asList(PlayerEntity.builder()
                    .name("Lionel Messi")
                    .club("PSG")
                    .position("RW")
                    .build(),
            PlayerEntity.builder()
                    .name("Angel Di Maria")
                    .club("Juventus")
                    .position("LW")
                    .build());

    List<PlayerEntity> francePlayerEntityList = Arrays.asList(PlayerEntity.builder()
                    .name("Kylian Mbappe")
                    .club("PSG")
                    .position("ST")
                    .build());

    List<TeamEntity> teamList = Arrays.asList(TeamEntity.builder()
            .name(ARGENTINA)
            .position(1)
            .nrOfCups(3)
            .players(argentinaPlayerEntityList)
            .build(), TeamEntity.builder()
            .name(FRANCE)
            .position(2)
            .nrOfCups(2)
            .players(francePlayerEntityList)
            .build());

    return teamList;
  }
}
