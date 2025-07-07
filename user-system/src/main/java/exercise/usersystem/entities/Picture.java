package exercise.usersystem.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String title;

    private String caption;

    @Column(nullable = false, unique = true)
    private String path;

    @ManyToMany
    @JoinTable(name = "pictures_albums",
            joinColumns = @JoinColumn(name = "pic_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums;
}
