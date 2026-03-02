package Moduuli7.tehtava7_4.dao;

import Moduuli7.tehtava7_4.entity.Transaction;
import Moduuli7.tehtava7_4.datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;

public class TransactionDao {


    public void persist(Transaction transaction) {
        // Haetaan yhteys (EntityManager)
        EntityManager em = MariaDbJpaConnection.getInstance();

        try {
            em.getTransaction().begin(); // Aloitetaan transaktio
            em.persist(transaction);      // Tallennetaan olio
            em.getTransaction().commit();  // Vahvistetaan muutokset
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Jos virhe, perutaan muutokset
            }
            e.printStackTrace();
        }
    }
}
