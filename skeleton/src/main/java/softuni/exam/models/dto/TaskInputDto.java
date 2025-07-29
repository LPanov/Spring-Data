package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class TaskInputDto {
    @XmlElement
    @NotNull
    private String date;

    @XmlElement
    @NotNull
    @Positive
    private BigDecimal price;

    @XmlElement(name = "car")
    private CarIdDto car;

    // Map to the nested MechanicDto
    @XmlElement(name = "mechanic")
    private MechanicDto mechanic;

    // Map to the nested PartIdDto
    @XmlElement(name = "part")
    private PartIdDto part;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarIdDto getCar() {
        return car;
    }

    public void setCar(CarIdDto car) {
        this.car = car;
    }

    public MechanicDto getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicDto mechanic) {
        this.mechanic = mechanic;
    }

    public PartIdDto getPart() {
        return part;
    }

    public void setPart(PartIdDto part) {
        this.part = part;
    }
}
