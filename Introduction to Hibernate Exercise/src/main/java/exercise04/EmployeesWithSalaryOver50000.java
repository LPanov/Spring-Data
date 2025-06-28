package exercise04;

import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<String> query = em.createQuery("select e.firstName from Employee e where e.salary > 50000", String.class);
        List<String> employees = query.getResultList();

        employees.forEach(System.out::println);


    }
}
