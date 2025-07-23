package exercise.cardealer.dtos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Getter;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierReportDto {
    @XmlAttribute
    private final Long id;
    @XmlAttribute
    private final String name;
    @XmlAttribute(name = "parts-count")
    private final Integer partsCount;

    public SupplierReportDto(Long id, String name, Integer partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

}
