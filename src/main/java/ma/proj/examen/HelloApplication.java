package ma.proj.examen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SelectionRepas.fxml"));
        primaryStage.setTitle("Restaurant en ligne");
        primaryStage.setScene(new Scene(root, 1090, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}