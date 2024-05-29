package de.lubowiecki.fxproducts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("products.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        /*
        Button b = new Button("Hallo");
        HBox hobx = new HBox(b);
        Scene scene = new Scene(hobx);
        */

        stage.setTitle("FxProducts");
        stage.setScene(scene);
        stage.show();

        /* // Einstellungen der JVM auslesen
        Properties props = System.getProperties();
        for(Object key : props.keySet()) {
            System.out.println(key + ": " + props.get(key));
        }
        */
    }

    public static void main(String[] args) {
        launch();
    }
}