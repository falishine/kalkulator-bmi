package com.mycompany.bmicalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;

public class BmiController {
    @FXML private TextField weightField;
    @FXML private TextField heightField;
    @FXML private Label resultValue;
    @FXML private Label categoryLabel;
    @FXML private Label resultMessage;
    @FXML private Label rangeLabel;
    @FXML private Label errorLabel;

    @FXML
    private void initialize() {
        showEmptyResult();
    }

    @FXML
    private void calculateBmi() {
        errorLabel.setText("");

        try {
            double weight = Double.parseDouble(weightField.getText().trim().replace(',', '.'));
            double heightCm = Double.parseDouble(heightField.getText().trim().replace(',', '.'));

            if (weight <= 0 || heightCm <= 0) {
                showError("Berat dan tinggi harus lebih dari 0.");
                return;
            }
            if (weight > 500 || heightCm < 50 || heightCm > 250) {
                showError("Periksa kembali angka berat atau tinggi badan.");
                return;
            }

            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);
            resultValue.setText(String.format(Locale.US, "%.1f", bmi));

            if (bmi < 18.5) {
                categoryLabel.setText("Underweight");
                categoryLabel.getStyleClass().setAll("category-pill", "category-under");
                resultMessage.setText("Berat badan berada di bawah rentang normal.");
            } else if (bmi < 25.0) {
                categoryLabel.setText("Normal");
                categoryLabel.getStyleClass().setAll("category-pill", "category-normal");
                resultMessage.setText("Bagus! BMI berada dalam rentang normal.");
            } else if (bmi < 30.0) {
                categoryLabel.setText("Overweight");
                categoryLabel.getStyleClass().setAll("category-pill", "category-over");
                resultMessage.setText("BMI berada di atas rentang normal.");
            } else {
                categoryLabel.setText("Obesity");
                categoryLabel.getStyleClass().setAll("category-pill", "category-obese");
                resultMessage.setText("BMI berada pada kategori obesitas.");
            }

            rangeLabel.setText("Rentang BMI dewasa: 18,5 – 24,9");
        } catch (NumberFormatException ex) {
            showError("Masukkan berat dan tinggi menggunakan angka.");
        }
    }

    @FXML
    private void resetForm() {
        weightField.clear();
        heightField.clear();
        errorLabel.setText("");
        showEmptyResult();
        weightField.requestFocus();
    }

    private void showEmptyResult() {
        resultValue.setText("--");
        categoryLabel.setText("Belum dihitung");
        categoryLabel.getStyleClass().setAll("category-pill", "category-neutral");
        resultMessage.setText("Masukkan data berat dan tinggi badan untuk melihat hasil.");
        rangeLabel.setText("Rentang BMI dewasa: 18,5 – 24,9");
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }
}
