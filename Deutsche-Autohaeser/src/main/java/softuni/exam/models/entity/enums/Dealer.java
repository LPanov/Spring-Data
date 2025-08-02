package softuni.exam.models.entity.enums;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

@Entity
@Table(name = "dealers")
public class Dealer extends BaseEntity{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Double salary;

    @Column(name = "average_monthly_turnover ", nullable = false)
    private Double averageTurnover;

    @Column
    private LocalDate birthday;

    @ManyToOne
    @JoinColumn(name = "offering_car_id")
    private Car offeringCar;

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

    public Double getAverageTurnover() {
        return averageTurnover;
    }

    public void setAverageTurnover(Double averageTurnover) {
        this.averageTurnover = averageTurnover;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Car getOfferingCar() {
        return offeringCar;
    }

    public void setOfferingCar(Car offeringCar) {
        this.offeringCar = offeringCar;
    }
}
