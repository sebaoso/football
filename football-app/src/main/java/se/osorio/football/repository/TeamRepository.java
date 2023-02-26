package se.osorio.football.repository;

import org.springframework.data.repository.CrudRepository;
import se.osorio.football.entity.TeamEntity;
import java.util.List;
public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
  List<TeamEntity> findAllByNameContaining(String name);
  List<TeamEntity> findAll();
}
