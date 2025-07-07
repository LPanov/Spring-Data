package exercise.usersystem.entities;

import exercise.usersystem.annotations.Email;
import exercise.usersystem.annotations.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{
    @Column(nullable = false, unique = true)
    @Length(min = 4, max = 30, message = "Username must be between 4 and 30 characters")
    private String username;

    @Password(minLength = 6,
            maxLength = 30,
            required = true,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbol = true,
            message = "Invalid password")
    private String password;

    @Email
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in", nullable = false)
    private LocalDateTime lastLogin;

    @Range(min = 1, max = 120)
    private Integer age;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "town_of_birth")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "town_of_living")
    private Town livingIn;

    @ManyToMany
    @JoinTable(name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "frien_id")
    )
    private Set<User> friends = new HashSet<User>();

    @ManyToMany(mappedBy = "friends")
    private Set<User> friendsOf = new HashSet<User>();

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    public User() {
        isDeleted = false;
    }

    @Transient
    public String getFullName() {
        if (firstName == null && lastName == null) {
            return null;
        }
        if (firstName == null) {
            return lastName;
        }
        if (lastName == null) {
            return firstName;
        }
        return firstName + " " + lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public Town getLivingIn() {
        return livingIn;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public Set<User> getFriendsOf() {
        return friendsOf;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public void setLivingIn(Town livingIn) {
        this.livingIn = livingIn;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void setFriendsOf(Set<User> friendsOf) {
        this.friendsOf = friendsOf;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
