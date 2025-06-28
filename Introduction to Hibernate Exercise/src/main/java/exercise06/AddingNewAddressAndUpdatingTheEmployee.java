package exercise06;

import entities.Address;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Scanner;

public class AddingNewAddressAndUpdatingTheEmployee {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String lastName = input.nextLine();

        EntityManager em = EntityManagerHelper.createEntityManager();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");

        em.getTransaction().begin();
        int affectedEmployees = 0;
        try {
            em.persist(newAddress);

            Query query = em.createQuery("UPDATE Employee e set e.address = :a where e.lastName = :ln")
                    .setParameter("a", newAddress)
                    .setParameter("ln", lastName);

            affectedEmployees = query.executeUpdate();

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        System.out.printf("%d employees updated.%n", affectedEmployees);

    }
}
