package se.osorio.football;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.osorio.football.model.Player;
import se.osorio.football.model.Team;
import se.osorio.football.repository.PlayerRepository;
import se.osorio.football.repository.TeamRepository;
import se.osorio.football.service.PlayerService;

import java.util.List;

@Slf4j
@Controller
public class FootballViewController {

  private final TeamRepository teamRepository;
  private final PlayerService playerService;

  @Autowired
  public FootballViewController(TeamRepository teamRepository,  PlayerService playerService) {
    this.teamRepository = teamRepository;
    this.playerService = playerService;
  }

  @GetMapping(path = "/football", produces = MediaType.TEXT_HTML_VALUE)
  public String teams(final Model model) {
    log.info("Inside teams GUI");

    List<Team> teamList = teamRepository.findAll();
    model.addAttribute("teamList", teamList);

    return "teams";
  }

  @GetMapping(path = "/players", produces = MediaType.TEXT_HTML_VALUE)
  public String players(@RequestParam String country, final Model model) {
    log.info("Inside players GUI");

    List<Player> playerList = playerService.getPlayersFromCountry(country);
    model.addAttribute("country", country);
    model.addAttribute("playerList", playerList);

    return "players";
  }





}
