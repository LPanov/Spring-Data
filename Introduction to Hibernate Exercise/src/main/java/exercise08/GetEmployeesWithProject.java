package exercise08;

import dtos.EmployeeSummaryDto;
import entities.Employee;
import entities.Project;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class GetEmployeesWithProject {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int employeeId = Integer.parseInt(input.nextLine());

        EntityManager em = EntityManagerHelper.createEntityManager();
        TypedQuery<EmployeeSummaryDto> employeeWithId = em.createQuery("select e.firstName, e.lastName, e.jobTitle from Employee e where e.id = :id", EmployeeSummaryDto.class)
                .setParameter("id", employeeId);

        EmployeeSummaryDto employee = employeeWithId.getSingleResult();

        TypedQuery<Project> employeeProjects = em.createQuery("select e.projects from Employee e where e.id = :id", Project.class)
                .setParameter("id", employeeId);

        List<Project> projects = employeeProjects.getResultList();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        projects.forEach(p -> System.out.println(p.getName()));


    }
}
