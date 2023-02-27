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

@Slf4j
@Service
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepository;
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAllByNameContaining(String name) {

        List<TeamEntity> teamEntities = teamRepository.findAllByNameContaining(name);

        List<Team> teamList = mapTeamEntitiesToTeamModel(teamEntities);
        return teamList;
    }

    private static List<Team> mapTeamEntitiesToTeamModel(List<TeamEntity> teamEntities) {
        List<Team> teamList = teamEntities.stream()
                .map(mapTeamEntityToTeamModel())
                .collect(Collectors.toList());
        return teamList;
    }

    private static Function<TeamEntity, Team> mapTeamEntityToTeamModel() {
        return teamEntity -> Team.builder()
                .id(teamEntity.getId())
                .name(teamEntity.getName())
                .position(teamEntity.getPosition())
                .nrOfCups(teamEntity.getNrOfCups())
                .players(mapPlayerEntitiesToPlayerModel(teamEntity))
                .build();
    }

    private static List<Player> mapPlayerEntitiesToPlayerModel(TeamEntity teamEntity) {
        return teamEntity.getPlayers().stream().map(playerEntity -> Player.builder()
                        .id(playerEntity.getId())
                        .name(playerEntity.getName())
                        .position(playerEntity.getPosition())
                        .club(playerEntity.getClub())
                        .team(teamEntity.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Team> findAll() {
        List<TeamEntity> teamEntities = teamRepository.findAll();
        List<Team> teamList = mapTeamEntitiesToTeamModel(teamEntities);
        return teamList;
    }

    public Team getTeam(Integer id) {
        TeamEntity teamEntity = teamRepository.findById(id).orElse(null);

        Team team = Team.builder()
                .id(teamEntity.getId())
                .name(teamEntity.getName())
                .position(teamEntity.getPosition())
                .nrOfCups(teamEntity.getNrOfCups())
                .players(mapPlayerEntitiesToPlayerModel(teamEntity))
                .build();

        return team;
    }

    public List<Player> getPlayersOfTeam(Integer id) {
        return getTeam(id).getPlayers();
    }

    public void save(Team team){
        TeamEntity teamEntity = TeamEntity.builder()
                .id(team.getId())
                .name(team.getName())
                .position(team.getPosition())
                .nrOfCups(team.getNrOfCups())
                .build();
        teamRepository.save(teamEntity);
    }
}
