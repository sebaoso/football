package se.osorio.football;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class FootballViewController {

  private final TeamRepository teamRepository;

  @Autowired
  public FootballViewController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @GetMapping(path = "/football", produces = MediaType.TEXT_HTML_VALUE)
  public String teams(final Model model) {
    log.info("Inside library GUI");

    List<Team> teamList = teamRepository.findAll();
    model.addAttribute("teamList", teamList);

    return "teams";
  }




}
