package Moduuli7.tehtava_7_3;

import Moduuli7.tehtava_7_3.dao.CurrencyDao;
import Moduuli7.tehtava_7_3.entity.Currency;

/**
 * Aja tämä luokka kerran lisätäksesi valuutat tietokantaan.
 */
public class AddTestData {
    public static void main(String[] args) {
        CurrencyDao dao = new CurrencyDao();

        System.out.println("Lisätään valuuttoja...");

        dao.addCurrency(new Currency("EUR", "Euro", 1.0));
        dao.addCurrency(new Currency("USD", "US Dollar", 1.08));
        dao.addCurrency(new Currency("GBP", "British Pound", 0.86));
        dao.addCurrency(new Currency("SEK", "Swedish Krona", 11.20));
        dao.addCurrency(new Currency("JPY", "Japanese Yen", 160.50));

        System.out.println("Valuutat lisätty!");
        System.out.println("\nTarkistetaan tietokannasta:");

        for (Currency c : dao.getAllCurrencies()) {
            System.out.println(c.getAbbreviation() + " - " + c.getName() + " (" + c.getRate() + ")");
        }

        System.exit(0);
    }
}

