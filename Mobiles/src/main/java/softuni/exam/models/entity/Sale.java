package softuni.exam.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sales")
@Getter
@Setter
public class Sale extends BaseEntity {
    @Column(name = "discounted")
    private Boolean discounted;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

}
