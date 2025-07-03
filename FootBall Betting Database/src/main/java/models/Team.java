package models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "logo", nullable = false, length = 500, unique = true)
    private String logo;

    @Column(length = 3, nullable = false)
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color", nullable = false)
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color", nullable = false)
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    private BigDecimal budget;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    @OneToMany(mappedBy = "homeTeam")
    private Set<Game> homeTeamGames;

    @OneToMany(mappedBy = "awayTeam")
    private Set<Game> awayTeamGames;
}
