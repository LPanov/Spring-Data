package helper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerHelper {
    public static EntityManager createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exercise");
        EntityManager em = emf.createEntityManager();

        return em;
    }
}
