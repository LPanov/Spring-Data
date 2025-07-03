package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "colors")
public class Color extends BaseEntity{
    @Column(name = "name", nullable = false)
    private String color;

    @OneToMany(mappedBy = "primaryKitColor")
    private Set<Team> primaryKitColors;

    @OneToMany(mappedBy = "secondaryKitColor")
    private Set<Team> secondKitColors;
}
