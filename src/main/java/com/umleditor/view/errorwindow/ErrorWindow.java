package com.umleditor.view.errorwindow;

import javafx.scene.control.Alert;

public class ErrorWindow {
    public static void showError(String header, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(error);

        alert.showAndWait();
    }
}
