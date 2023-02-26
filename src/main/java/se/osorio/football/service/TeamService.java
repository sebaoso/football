package se.osorio.football.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.osorio.football.model.Team;
import se.osorio.football.repository.TeamRepository;

import java.util.List;

@Slf4j
@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    List<Team> findAllByNameContaining(String name){
        return teamRepository.findAllByNameContaining(name);
    }
    List<Team> findAll(){
        return teamRepository.findAll();
    }
}
