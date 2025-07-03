package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "competitions")
public class Competition extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "competition_type")
    private CompetitionType competitionType;

    @OneToMany(mappedBy = "competition")
    private Set<Game> games;
}
