package exercise.usersystem.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;
}
