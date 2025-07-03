package models;

import jakarta.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @Column(length = 2)
    private String id;

    @Column(name = "position_description", length = 300)
    private String description;

    @OneToOne(mappedBy = "position")
    private Player player;
}
