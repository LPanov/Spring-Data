package models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bets")
public class Bet extends BaseEntity{
    @Column(nullable = false, name = "bet_money")
    private BigDecimal betAmount;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "bet")
    private Set<BetGame> betGames;

}
