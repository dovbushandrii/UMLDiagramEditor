/**
 * @author Andrii Dovbush xdovbu00
 * @author Anastasiia Oberemko xobere00
 *
 * @file ErrorWindow.java
 */
package com.umleditor.view.errorwindow;

import javafx.scene.control.Alert;

/**
 * Constructs Error alert
 */
public class ErrorWindow {
    public static void showError(String header, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(error);

        alert.showAndWait();
    }
}
