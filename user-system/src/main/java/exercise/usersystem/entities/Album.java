package exercise.usersystem.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity{
    private String name;

    @Column(name = "background_color")
    private String hexCode;

    @Column(nullable = false, name = "is_public")
    private Boolean isPublic;

    @ManyToMany(mappedBy = "albums")
    private Set<Picture> pictures;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
