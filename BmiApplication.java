package com.mycompany.bmicalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BmiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                BmiApplication.class.getResource("/com/mycompany/bmicalculator/bmi-view.fxml")
        );
        Scene scene = new Scene(loader.load(), 900, 680);
        scene.getStylesheets().add(
                BmiApplication.class.getResource("/com/mycompany/bmicalculator/styles.css").toExternalForm()
        );

        stage.setTitle("BMI Calculator");
        stage.setMinWidth(760);
        stage.setMinHeight(620);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
