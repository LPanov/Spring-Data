package models;

import jakarta.persistence.*;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @EmbeddedId
    private BetGameId id;

    @ManyToOne
    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "bet_id", insertable = false, updatable = false)
    private Bet bet;

    @ManyToOne
    @JoinColumn(name = "result_prediction")
    private ResultPrediction resultPrediction;

}
