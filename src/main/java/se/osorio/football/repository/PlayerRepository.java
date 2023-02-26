package se.osorio.football.repository;

import org.springframework.data.repository.CrudRepository;
import se.osorio.football.model.Player;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    List<Player> findAllByCountryContaining(String country);
    List<Player> findAll();
}
