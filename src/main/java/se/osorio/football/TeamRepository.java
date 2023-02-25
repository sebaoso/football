package se.osorio.football;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

  List<Team> findAllByNameContaining(String name);
  List<Team> findAll();
}
