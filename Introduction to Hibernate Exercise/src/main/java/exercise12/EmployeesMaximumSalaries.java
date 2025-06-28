package exercise12;

import dtos.DepNameSalaryDto;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<DepNameSalaryDto> query = em.createQuery("select max(e.salary), e.department.name from Employee e group by e.department having max(salary) not between 30000 and 70000", DepNameSalaryDto.class);
        List<DepNameSalaryDto> resultList = query.getResultList();

        for (DepNameSalaryDto department : resultList) {
            System.out.printf("%s %.2f%n", department.getDepName(), department.getSalary());
        }

    }
}
