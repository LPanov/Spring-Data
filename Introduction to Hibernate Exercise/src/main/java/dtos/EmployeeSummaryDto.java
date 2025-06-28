package dtos;

import java.math.BigDecimal;

public class EmployeeSummaryDto {
    private String firstName;
    private String lastName;
    private String departmentName;
    private String jobTitle;
    private BigDecimal salary;

    public EmployeeSummaryDto(String firstName, String lastName, String departmentName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentName = departmentName;
        this.salary = salary;
    }

    public EmployeeSummaryDto(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public EmployeeSummaryDto(String firstName, String lastName, String jobTitle) {
        this.jobTitle = jobTitle;
        this.lastName = lastName;
        this.firstName = firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
