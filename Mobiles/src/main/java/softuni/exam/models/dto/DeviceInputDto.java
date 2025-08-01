package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceInputDto {
    @XmlElement
    @NotNull
    @Length(min=2, max=20)
    private String brand;

    @XmlElement(name = "device_type")
    private String deviceType;

    @XmlElement
    @NotNull
    @Length(min=1, max=20)
    private String model;

    @XmlElement
    @Positive
    private Double price;

    @XmlElement
    @Positive
    private Integer storage;

    @XmlElement(name = "sale_id")
    private Long sale;
}
