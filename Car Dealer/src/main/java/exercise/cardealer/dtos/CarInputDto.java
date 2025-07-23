package exercise.cardealer.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CarInputDto {
    @XmlElement
    private String make;
    @XmlElement
    private String model;
    @XmlElement(name = "travelled-distance")
    private Long travelledDistance;
}
