package Moduuli7.tehtava_7_2.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Moduuli7.tehtava_7_2.datasource.MariaDbConnection;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tehtava_7_2/view.fxml"));
        Parent root = loader.load();

        stage.setTitle("Valuuttamuunnin DB");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void stop() {
        MariaDbConnection.terminate();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

