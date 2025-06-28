package exercise13;

import entities.Address;
import entities.Town;
import helper.EntityManagerHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String townName =  input.nextLine();

        EntityManager em = EntityManagerHelper.createEntityManager();

        TypedQuery<Town> townQuery = em.createQuery("select t from Town t where t.name = :tn", Town.class)
                .setParameter("tn", townName);

        Town town = townQuery.getSingleResult();

        TypedQuery<Address> addressesQuery = em.createQuery("select a from Address a where a.town = :t", Address.class)
                .setParameter("t", town);
        List<Address> addresses = addressesQuery.getResultList();

        em.getTransaction().begin();

        int removedAddresses = 0;
        try
        {
            for (Address address : addresses) {
                removedAddresses++;
                em.remove(address);
            }
            em.remove(town);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }

        System.out.printf("%d address in %s deleted%n", removedAddresses, townName);

    }
}
