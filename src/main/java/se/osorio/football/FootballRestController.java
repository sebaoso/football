package se.osorio.football;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;

import java.util.List;

@Slf4j
@RestController
public class FootballRestController {

  private final TeamRepository teamRepository;

  @Autowired
  public FootballRestController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @GetMapping(path = "/teams")
  @Operation(summary = "",
      description = "Hämta alla lag.",
      tags = {"Hämta lag"},
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Svar vid giltigt anrop med inparametrar i korrekt format",
              content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
          @ApiResponse(responseCode = "400",
              description = "Bad Request",
              content = @Content()),
          @ApiResponse(responseCode = "500",
              description = "Internal Server Error",
              content = @Content()),
          @ApiResponse(responseCode = "503",
              description = "Service Unavailable - Fel vid anrop till underliggande tjänster",
              content = @Content())})
  public List<Team> getAllTeams() {
    log.info("Try to get all teams");
    return teamRepository.findAll();
  }

  @PostMapping(path = "/add")
  @Operation(summary = "",
      description = "lägg till lag.",
      tags = {"Lägg till lag"},
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Svar vid giltigt anrop med inparametrar i korrekt format",
              content = @Content()),
          @ApiResponse(responseCode = "400",
              description = "Bad Request",
              content = @Content()),
          @ApiResponse(responseCode = "500",
              description = "Internal Server Error",
              content = @Content()),
          @ApiResponse(responseCode = "503",
              description = "Service Unavailable - Fel vid anrop till underliggande tjänster",
              content = @Content())})
  public void addTeam(@RequestBody Team team) {
    log.info("Try to get all teams");
    teamRepository.save(team);
  }



  @GetMapping(path = "/teams/{name}")
  @Operation(summary = "",
      description = "Hämta lag utifrån namn.",
      tags = {"Hämta lag utifrån namn"},
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Svar vid giltigt anrop med inparametrar i korrekt format",
              content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))),
          @ApiResponse(responseCode = "400",
              description = "Bad Request",
              content = @Content()),
          @ApiResponse(responseCode = "500",
              description = "Internal Server Error",
              content = @Content()),
          @ApiResponse(responseCode = "503",
              description = "Service Unavailable - Fel vid anrop till underliggande tjänster",
              content = @Content())})
  public List<Team> getTeamsByName(@PathVariable String name) {
    log.info("Try to get team by name {}", name);
    return teamRepository.findAllByNameContaining(name);
  }
}
