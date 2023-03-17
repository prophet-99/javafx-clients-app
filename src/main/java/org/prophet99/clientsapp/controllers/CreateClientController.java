package org.prophet99.clientsapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.prophet99.clientsapp.data.models.entities.Client;
import org.prophet99.clientsapp.data.services.implementations.ClientServiceImpl;
import org.prophet99.clientsapp.data.services.interfaces.IClientService;
import org.prophet99.clientsapp.utils.CustomAlert;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
  // LOGIC ATTRIBUTES
  private IClientService clientService = new ClientServiceImpl();
  public boolean isCreated = false;
  // JFX ATTRIBUTES
  @FXML
  private Button clBtnSave;
  @FXML
  private TextField clTxtEmail;
  @FXML
  private TextField clTxtFirstName;
  @FXML
  private TextField clTxtLastName;
  @FXML
  private TextField clTxtPhone;

  @Override
  public void initialize(URL location, ResourceBundle resources) { }

  @FXML
  void saveClient(ActionEvent event) {
    String email = clTxtEmail.getText().trim();
    String firstName = clTxtFirstName.getText().trim();
    String lastName = clTxtLastName.getText().trim();
    String phone = clTxtPhone.getText().trim();

    if (firstName.equals("") || lastName.equals("") || email.equals("") || phone.equals("")){
      CustomAlert.showCustomAlert(Alert.AlertType.ERROR, "Error", "Should fill all fields");
    } else {
      String result = clientService.save(new Client("", firstName, lastName, email, phone));
      CustomAlert.showCustomAlert(Alert.AlertType.INFORMATION, "Information", result);

      isCreated = true;
      Stage stage = (Stage) clBtnSave.getScene().getWindow();
      stage.close();
    }
  }
}
