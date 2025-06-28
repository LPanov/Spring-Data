package exercise10;

import dtos.EmployeeSummaryDto;
import entities.Employee;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        em.getTransaction().begin();
        try {
            Query query = em.createQuery("UPDATE Employee e set salary = salary*1.12 where e.department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')");
            query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        TypedQuery<EmployeeSummaryDto> query = em.createQuery("select e.firstName, e.lastName, e.salary from Employee e where e.department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", EmployeeSummaryDto.class);
        List<EmployeeSummaryDto> resultList = query.getResultList();
        for (EmployeeSummaryDto employee : resultList) {
            System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }
    }
}
