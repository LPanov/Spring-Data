package models;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic{
    @EmbeddedId
    private StatisticId id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false, insertable=false, updatable=false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false, insertable=false, updatable=false)
    private Game game;

    @Column(name = "scored_goals")
    private Integer scoredGoals;

    @Column(name = "player_assists")
    private Integer playerAssists;

    @Column(name = "played_minutes")
    private Integer playedMinutes;
}
