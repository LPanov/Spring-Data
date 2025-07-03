package models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "continents")
public class Continent extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "continents")
    private Set<Country> countries;
}
