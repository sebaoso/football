package se.osorio.football;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import se.osorio.football.controller.FootballRestController;
import se.osorio.football.model.Player;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;
import se.osorio.football.service.PlayerService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@SpringBootTest(classes = { FootballApplication.class })
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FootballApplicationTests {

  @MockBean
  private PlayerService playerServiceMock;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private MockMvc mvc;

  @BeforeEach
  public void initialiseRestAssuredMockMvcStandalone(){
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetTeamsByName() {
    FootballRestController controller = new FootballRestController(teamRepository);
    List<Team> teams = controller.getTeamsByName("Argentina");
    assertEquals(1, teams.size());
  }

  @Test
  public void givenStartpage_shouldExistTitle() throws Exception {

    mvc.perform(MockMvcRequestBuilders.get("/football"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("FIFA World Cup 2022")));
  }

  @Test
  public void givenCountryArgentina_shouldExistMessi() throws Exception {

    List<Player> playerList = Arrays.asList(Player.builder().name("Lionel Messi").club("PSG").position("RW").country("Argentina").build(),
            Player.builder().name("Angel Di Maria").club("Juventus").position("LW").country("Argentina").build());

    when(playerServiceMock.getPlayersFromCountry("Argentina")).thenReturn(playerList);

    mvc.perform(MockMvcRequestBuilders.get("/players?country=Argentina"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("Lionel Messi")));
  }


}
