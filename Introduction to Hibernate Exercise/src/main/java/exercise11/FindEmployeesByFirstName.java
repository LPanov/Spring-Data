package exercise11;

import dtos.EmployeeNameJobSalaryDto;
import dtos.EmployeeSummaryDto;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String pattern = input.nextLine();

        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<EmployeeNameJobSalaryDto> query = em.createQuery("select e.firstName, e.lastName, e.jobTitle, e.salary from Employee e where e.firstName like concat(:p, '%')", EmployeeNameJobSalaryDto.class)
                .setParameter("p", pattern);
        List<EmployeeNameJobSalaryDto> resultList = query.getResultList();

        for (EmployeeNameJobSalaryDto employee : resultList) {
            System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary());
        }
    }
}
