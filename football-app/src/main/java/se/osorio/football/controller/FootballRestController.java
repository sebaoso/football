package se.osorio.football.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.osorio.football.model.Player;
import se.osorio.football.model.Team;
import se.osorio.football.service.PlayerService;
import se.osorio.football.service.TeamService;

import java.util.List;

@Slf4j
@RestController
public class FootballRestController {

  private final TeamService teamService;

  private final PlayerService playerService;

  @Autowired
  public FootballRestController(TeamService teamService, PlayerService playerService) {
    this.teamService = teamService;
    this.playerService = playerService;
  }

  @GetMapping(path = "/teams")
  @Operation(summary = "",
      description = "Hämta alla lag.",
      tags = {"Hämta alla lag"},
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Svar vid giltigt anrop med inparametrar i korrekt format",
              content = {
                  @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
              }),
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
    return teamService.findAll();
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
    teamService.save(team);
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
    return teamService.findAllByNameContaining(name);
  }

  @GetMapping(path = "/players")
  @Operation(summary = "",
          description = "Hämta alla spelare.",
          tags = {"Hämta alla spelare"},
          responses = {
                  @ApiResponse(responseCode = "200",
                          description = "Svar vid giltigt anrop med inparametrar i korrekt format",
                          content = {
                                  @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
                          }),
                  @ApiResponse(responseCode = "400",
                          description = "Bad Request",
                          content = @Content()),
                  @ApiResponse(responseCode = "500",
                          description = "Internal Server Error",
                          content = @Content()),
                  @ApiResponse(responseCode = "503",
                          description = "Service Unavailable - Fel vid anrop till underliggande tjänster",
                          content = @Content())})
  public List<Player> getAllPlayers() {
    log.info("Try to get all players");
    return playerService.findAll();
  }

}
