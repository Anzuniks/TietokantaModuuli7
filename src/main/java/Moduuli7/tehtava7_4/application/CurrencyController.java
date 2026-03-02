package Moduuli7.tehtava7_4.application;


import Moduuli7.tehtava7_4.dao.CurrencyDao;
import Moduuli7.tehtava7_4.entity.Currency;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Moduuli7.tehtava7_4.dao.TransactionDao;
import Moduuli7.tehtava7_4.entity.Transaction;
import java.util.List;

public class CurrencyController {

    @FXML private TextField amountField;
    @FXML
    private ComboBox<String> currencySelector;
    @FXML private Label resultLabel;

    private CurrencyDao currencyDao = new CurrencyDao();
    private TransactionDao transactionDao = new TransactionDao();

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
            Currency targetCurr = currencyDao.getCurrency(selectAbbr);
            Currency sourceCurr = currencyDao.getCurrency("EUR");

            if (targetCurr != null && sourceCurr != null) {

                double result = amount * targetCurr.getRate();
                resultLabel.setText(String.format("%.2f %s", result, selectAbbr));

                Transaction transaction = new Transaction(amount, sourceCurr, targetCurr);
                transactionDao.persist(transaction);

                System.out.println("Muunnos tallennettu tietokantaan!");
            }
        } catch (NumberFormatException e) {
            showError("Syötä kelvollinen määrä.");
        }
    }


    @FXML
    public void handleAddCurrency() {
        try {
            Stage newStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tehtava7_4/addCurrency.fxml"));
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

