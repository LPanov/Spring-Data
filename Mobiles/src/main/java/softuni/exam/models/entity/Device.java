package softuni.exam.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "devices")
@Getter
@Setter
public class Device extends BaseEntity{
    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type")
    private DeviceType deviceType;

    @Column(nullable = false, unique = true)
    private String model;

    @Basic
    private Double price;

    @Basic
    private Integer storage;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
}
