package dtos;

import java.math.BigDecimal;

public class DepNameSalaryDto {
    private final String depName;
    private final BigDecimal salary;

    public DepNameSalaryDto(BigDecimal salary, String depName) {
        this.depName = depName;
        this.salary = salary;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getDepName() {
        return depName;
    }
}
