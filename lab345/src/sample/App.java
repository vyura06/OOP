package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static <T extends Controller> T create(String s) {
        FXMLLoader l = new FXMLLoader();
        try {
            l.load(Main.class.getClassLoader().getResourceAsStream(s));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return l.getController();
    }

    public static void completeAlert(String message, Alert.AlertType type) {
        alert("Complete!", message, "", type);
    }
    public static void errorAlert(String message) {
        alert("Error", message, "An error occurred during execution.", Alert.AlertType.ERROR);
    }
    public static void alert(String title, String message, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private static Main main;
    public static Main getMain() {
        return main;
    }

    public void start(Stage stage) {
        main = Main.create();
        stage.setScene(new Scene(main.getRoot()));
        stage.setTitle("Transport");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}