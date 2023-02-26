package se.osorio.football.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Player")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Schema(description = "Namn", type="string")
    private String name;
    @Schema(description = "Position", type="string")
    private String position;
    @Schema(description = "Klubb", type="string")
    private String club;
    @ManyToOne
    @JoinColumn(name ="team_id", nullable = false)
    private TeamEntity team;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", club='" + club + '\'' +
                '}';
    }
}

