package Moduuli7.tehtava_7_2.dao;

import Moduuli7.tehtava_7_2.entity.Currency;
import Moduuli7.tehtava_7_2.datasource.MariaDbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CurrencyDao {

    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rate FROM currency";

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getDouble("rate")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    public Currency getCurrency(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rate FROM currency WHERE abbreviation = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, abbreviation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Currency(
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getDouble("rate")
                );
            }
        } catch (SQLException e) {
        }
        return null;
    }
}

