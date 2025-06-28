package dtos;

import java.math.BigDecimal;

public class EmployeeNameJobSalaryDto {
    private final String firstName;
    private final String lastName;
    private final String jobTitle;
    private final BigDecimal salary;

    public EmployeeNameJobSalaryDto(String firstName, String lastName, String jobTitle, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
