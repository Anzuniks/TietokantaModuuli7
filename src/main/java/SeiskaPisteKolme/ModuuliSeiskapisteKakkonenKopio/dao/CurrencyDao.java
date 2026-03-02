package SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.dao;

import SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.datasource.MariaDbJpaConnection;
import SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.entity.Currency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class CurrencyDao {

    public List<Currency> getAllCurrencies() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        List<Currency> currencies = em.createQuery("SELECT c FROM Currency c", Currency.class).getResultList();
        return currencies;
    }

    public Currency getCurrency(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.find(Currency.class, abbreviation);
    }

    public void addCurrency(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

}