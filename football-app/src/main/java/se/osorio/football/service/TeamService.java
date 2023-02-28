package se.osorio.football.service;

import se.osorio.football.model.Player;
import se.osorio.football.model.Team;

import java.util.List;


public interface TeamService {
    public List<Team> findAllByNameContaining(String name);
    public List<Team> findAll();
    public Team getTeam(Integer id);
    public List<Player> getPlayersOfTeam(Integer id);
    public void save(Team team);
}
