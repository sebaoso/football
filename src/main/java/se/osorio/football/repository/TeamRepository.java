package se.osorio.football.repository;

import org.springframework.data.repository.CrudRepository;
import se.osorio.football.model.Team;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {
  List<Team> findAllByNameContaining(String name);
  List<Team> findAll();
}
