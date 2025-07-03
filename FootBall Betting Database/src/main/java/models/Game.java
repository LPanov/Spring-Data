package models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team")
    private Team awayTeam;

    @Column(name = "home_team_goals")
    private int homeTeamGoals;

    @Column(name = "away_team_goals")
    private int awayTeamGoals;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "home_team_win_bet_rate")
    private Double homeTeamWinBetRate;

    @Column(name = "away_team_win_bet_rate")
    private Double awayTeamWinBetRate;

    @Column(name = "draw_game_bet_rate")
    private Double drawTeamWinBetRate;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToMany(mappedBy = "game")
    private Set<BetGame> betGames;

    @OneToMany(mappedBy = "game")
    private Set<PlayerStatistic> statistics;
}
