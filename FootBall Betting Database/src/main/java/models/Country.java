package models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(length = 3)
    private String id;

    @Column(nullable = false,  length = 300, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
    private Set<Continent> continents;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
