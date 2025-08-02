package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import org.hibernate.validator.constraints.Length;

@XmlAccessorType(XmlAccessType.FIELD)
public class DealerInputDto {
    @XmlElement(name = "first_name")
    @NotNull
    @Length(min = 2, max = 30)
    private String firstName;

    @XmlElement(name = "last_name")
    @NotNull
    @Length(min = 2, max = 30)
    private String lastName;

    @XmlElement
    @NotNull
    @Positive
    private Double salary;

    @XmlElement
    private String birthday;

    @XmlElement(name = "average_monthly_turnover")
    @NotNull
    @Positive
    private Double averageMonthlyTurnover;

    @XmlElement(name = "offering_car_id")
    private Long offeringCar;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Double getAverageMonthlyTurnover() {
        return averageMonthlyTurnover;
    }

    public void setAverageMonthlyTurnover(Double averageMonthlyTurnover) {
        this.averageMonthlyTurnover = averageMonthlyTurnover;
    }

    public Long getOfferingCar() {
        return offeringCar;
    }

    public void setOfferingCar(Long offeringCar) {
        this.offeringCar = offeringCar;
    }
}
