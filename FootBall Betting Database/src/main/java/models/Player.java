package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "players")
public class Player extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(name = "squad_number")
    private String squadNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Column(name = "is_injured")
    private Boolean isInjured;

    @OneToMany(mappedBy = "player")
    private Set<PlayerStatistic> statistics;
}
