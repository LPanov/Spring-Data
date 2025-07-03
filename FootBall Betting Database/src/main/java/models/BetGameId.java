package models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BetGameId implements Serializable {

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "bet_id")
    private Long betId;

    // Default constructor
    public BetGameId() {}

    public BetGameId(Long gameId, Long betId) {
        this.gameId = gameId;
        this.betId = betId;
    }

    // Getters and setters
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    // equals and hashCode are required for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetGameId betGameId = (BetGameId) o;
        return Objects.equals(gameId, betGameId.gameId) &&
                Objects.equals(betId, betGameId.betId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, betId);
    }
}

