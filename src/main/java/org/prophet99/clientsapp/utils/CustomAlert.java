package org.prophet99.clientsapp.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CustomAlert {
  public static Optional<ButtonType> showCustomAlert(Alert.AlertType alertType, String title, String content) {
    Alert alert = new Alert(alertType);
    alert.setHeaderText("👨‍💼 Clients App");
    alert.setTitle(title);
    alert.setContentText(content);
    return alert.showAndWait();
  }
}
