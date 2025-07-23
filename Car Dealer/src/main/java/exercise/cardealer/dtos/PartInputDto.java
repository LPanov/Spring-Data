package exercise.cardealer.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

import java.math.BigDecimal;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PartInputDto {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private BigDecimal price;
    @XmlAttribute
    private Integer quantity;
}
