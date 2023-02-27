package se.osorio.football.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.osorio.football.entity.PlayerEntity;
import se.osorio.football.model.Player;
import se.osorio.football.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PlayerServiceImpl implements PlayerService{
private final PlayerRepository playerRepository;
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public List<Player> findAll(){
        List<PlayerEntity> playerEntities = playerRepository.findAll();

        List<Player> playerList = playerEntities.stream()
                .map(playerEntity -> Player.builder()
                        .id(playerEntity.getId())
                        .name(playerEntity.getName())
                        .position(playerEntity.getPosition())
                        .club(playerEntity.getClub())
                        .team(playerEntity.getTeam().getName())
                        .build())
                .collect(Collectors.toList());

        return playerList;
    }
}
