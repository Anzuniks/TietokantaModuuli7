package Moduuli7.tehtava_7_3.application;

import Moduuli7.tehtava_7_3.dao.CurrencyDao;
import Moduuli7.tehtava_7_3.entity.Currency;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddCurrencyController {

    @FXML private TextField abbreviationField;
    @FXML private TextField nameField;
    @FXML private TextField rateField;

    private CurrencyDao currencyDao = new CurrencyDao();

    @FXML
    public void handleAdd() {
    try {
        String abbreviation  = abbreviationField.getText();
        String name = nameField.getText();
        double rate = Double.parseDouble(rateField.getText());

        Currency currency = new Currency(abbreviation, name, rate);
        currencyDao.addCurrency(currency);

        Stage stage = (Stage) abbreviationField.getScene().getWindow();
        stage.close();
    } catch (NumberFormatException e) {
        showError("Syötä kelvollinen kurssi.");
    } catch (Exception e) {
        showError("Virhe: " + e.getMessage());
    }

}

private void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Virhe");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
    }
}

