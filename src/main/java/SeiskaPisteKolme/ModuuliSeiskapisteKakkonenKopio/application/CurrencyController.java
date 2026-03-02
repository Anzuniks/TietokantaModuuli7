package SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.application;


import SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.dao.CurrencyDao;
import SeiskaPisteKolme.ModuuliSeiskapisteKakkonenKopio.entity.Currency;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class CurrencyController {

    @FXML private TextField amountField;
    @FXML
    private ComboBox<String> currencySelector;
    @FXML private Label resultLabel;

    private CurrencyDao currencyDao = new CurrencyDao();

    @FXML
    public void initialize() {
        List<Currency> currencies = currencyDao.getAllCurrencies();

        if (currencies != null) {
            for (Currency c : currencies) {
                currencySelector.getItems().add(c.getAbbreviation());
            }
        } else {
            showError("Tietokantayhteys lataus epäonnistui.");
        }
    }


    @FXML
    public void handleConvert() {
        String selectAbbr = currencySelector.getValue();

        if (selectAbbr == null || amountField.getText().isEmpty()) {
            showError("Valitse valuutta ja syötä määrä.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountField.getText());
            Currency curr = currencyDao.getCurrency(selectAbbr);

            if (curr != null) {
                double result = amount * curr.getRate();
                resultLabel.setText(String.format("%.2f %s", result, selectAbbr));
            }
        } catch (NumberFormatException e) {
            showError("Syötä kelvollinen määrä.");
        }
    }


    @FXML
    public void handleAddCurrency() {
        try {
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addCurrency.fxml"));
            Parent root = loader.load();
            newStage.setTitle("Lisää Valuutta");
            newStage.setScene(new Scene(root));
            newStage.showAndWait();

            currencySelector.getItems().clear();
            initialize();
        } catch (Exception e) {
            showError("Ikkunan avaus epäonnistui: " + e.getMessage());
        }
    }

    private void showError(String messaage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Virhe");
        alert.setHeaderText(null);
        alert.setContentText(messaage);
        alert.showAndWait();
    }
}
