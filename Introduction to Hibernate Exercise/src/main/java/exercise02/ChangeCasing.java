package exercise02;

import entities.Address;
import entities.Town;
import jakarta.persistence.*;

import java.util.List;


public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercise");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Town> query = em.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> result = query.getResultList();

        em.getTransaction().begin();

        try {
            for (Town town : result) {
                if (town.getName().length() > 5) {
                    em.detach(town);
                } else {
                    town.setName(town.getName().toUpperCase());
                    em.persist(town);
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
