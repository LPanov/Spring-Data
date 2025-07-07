package exercise.usersystem.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "bornTown")
    private Set<User> usersBornIn;

    @OneToMany(mappedBy = "livingIn")
    private Set<User> usersLivingIn;
}
