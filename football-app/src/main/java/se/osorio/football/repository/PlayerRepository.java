package se.osorio.football.repository;

import org.springframework.data.repository.CrudRepository;
import se.osorio.football.entity.PlayerEntity;
import java.util.List;
public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {
    List<PlayerEntity> findAll();
}
