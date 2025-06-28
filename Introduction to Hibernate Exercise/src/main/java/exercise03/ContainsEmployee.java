package exercise03;

import entities.Employee;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");

        String firstName = input[0];
        String lastName = input[1];


        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<Long> query = em.createQuery("select count(*) from Employee as e where e.firstName = :fn and e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName);

       Long count = query.getSingleResult();

       if (count == 0) {
           System.out.println("No");
       } else {
           System.out.println("Yes");
       }
    }
}
