package exercise05;

import dtos.EmployeeSummaryDto;
import entities.Employee;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesFromDepartment {
    private static final String DEPARTMENT_NAME = "Research and Development";

    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<EmployeeSummaryDto> query = em.createQuery("select e.firstName, e.lastName, e.department.name, e.salary from Employee e where e.department.name = :dn order by e.salary, e.id", EmployeeSummaryDto.class)
                .setParameter("dn", DEPARTMENT_NAME);
        List<EmployeeSummaryDto> employees = query.getResultList();

        for (EmployeeSummaryDto employee : employees) {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(), employee.getLastName(), employee.getDepartmentName(), employee.getSalary());
        }
    }
}
