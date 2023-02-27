package se.osorio.football.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.osorio.football.entity.TeamEntity;
import se.osorio.football.model.Player;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public interface TeamService {
    public List<Team> findAllByNameContaining(String name);
    public List<Team> findAll();
    public Team getTeam(Integer id);
    public List<Player> getPlayersOfTeam(Integer id);
    public void save(Team team);
}
