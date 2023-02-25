package se.osorio.football.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Schema(description = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String position;
    private String club;
    private String country;

    public Player(Integer id, String name, String position, String club, String country) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.club = club;
        this.country = country;
    }

    public Player() {}
}

