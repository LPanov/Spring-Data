package exercise.cardealer.dtos;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExtendedDto {
    @XmlTransient
    private Long id;

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlElementWrapper(name = "parts")
    @XmlElement(name = "part")
    private List<PartDto> parts;

}
