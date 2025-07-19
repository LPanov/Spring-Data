package exercise.productshop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column
    private Integer age;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "users_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> boughtProducts;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> soldProducts;


}
