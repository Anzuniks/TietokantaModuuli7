package Moduuli7.tehtava7_4.datasource;

import jakarta.persistence.*;

public class MariaDbJpaConnection {


    private static EntityManagerFactory emf = null;
    private static  EntityManager em = null;


    public static EntityManager getInstance() {
        if (em == null) {
            if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CurrencyMariaDbUnit74");
        }
        em = emf.createEntityManager();
    }
        return em;

    }

    public static void terminate() {
        if (em != null) {
            em.close();
            em = null;
        }
        if (emf != null) {
            emf.close();
            emf = null;
        }
    }
}

