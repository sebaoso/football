package se.osorio.football.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import se.osorio.football.model.Player;
import se.osorio.football.repository.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Service
public class PlayerService {
private final PlayerRepository playerRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayersFromCountry(String country){
       return playerRepository.findAll().stream()
               .filter(player -> player.getCountry().equals(country))
               .collect(Collectors.toList());
    }
}
