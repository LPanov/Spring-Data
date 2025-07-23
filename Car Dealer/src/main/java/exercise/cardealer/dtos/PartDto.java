package exercise.cardealer.dtos;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PartDto {
    @XmlTransient
    private Long id;

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlTransient
    private Integer quantity;
}
