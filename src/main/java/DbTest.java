import java.sql.*;

public class DbTest {
    public static void main(String [] args) {
        String url = "jdbc:mariadb://localhost:3306/currency_db";
        String user = "appuser";
        String password = "salasana123";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Yhteys onnistui!");

            // Haetaan valuutat
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM currency");

            System.out.println("\nTietokannan valuutat:");
            while (rs.next()) {
                System.out.println(rs.getString("abbreviation") + ": " +
                        rs.getString("name") + " (Kurssi: " +
                        rs.getDouble("rate") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
}