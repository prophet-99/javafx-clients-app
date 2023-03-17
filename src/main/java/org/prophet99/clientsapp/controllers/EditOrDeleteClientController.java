package org.prophet99.clientsapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.prophet99.clientsapp.data.models.entities.Client;
import org.prophet99.clientsapp.data.services.implementations.ClientServiceImpl;
import org.prophet99.clientsapp.data.services.interfaces.IClientService;
import org.prophet99.clientsapp.utils.CustomAlert;

import java.net.URL;
import java.util.ResourceBundle;

public class EditOrDeleteClientController implements Initializable {
  // LOGIC ATTRIBUTES
  private IClientService clientService = new ClientServiceImpl();
  public boolean isModified = false;
  private Client clientToEdit;
  // JFX ATTRIBUTES
  @FXML
  private Button clBtnDelete;
  @FXML
  private Button clBtnUpdate;
  @FXML
  private TextField clTxtCode;
  @FXML
  private TextField clTxtEmail;
  @FXML
  private TextField clTxtFirstName;
  @FXML
  private TextField clTxtLastName;
  @FXML
  private TextField clTxtPhone;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void loadClient(Client clientToEdit) {
    this.clientToEdit = clientToEdit;

    clTxtCode.setText(clientToEdit.code());
    clTxtFirstName.setText(clientToEdit.firstName());
    clTxtLastName.setText(clientToEdit.lastName());
    clTxtEmail.setText(clientToEdit.email());
    clTxtPhone.setText(clientToEdit.phone());
  }

  @FXML
  void updateClient(ActionEvent event) {
    String firstName = clTxtFirstName.getText().trim();
    String lastName = clTxtLastName.getText().trim();
    String email = clTxtEmail.getText().trim();
    String phone = clTxtPhone.getText().trim();

    if (firstName.equals("") || lastName.equals("") || email.equals("") || phone.equals("")){
      CustomAlert.showCustomAlert(Alert.AlertType.ERROR, "Error", "Should fill all fields");
    } else {
      String result = clientService.save(new Client(clientToEdit.code(), firstName, lastName, email, phone));
      CustomAlert.showCustomAlert(Alert.AlertType.INFORMATION, "Information", result);

      isModified = true;
      Stage stage = (Stage) clBtnUpdate.getScene().getWindow();
      stage.close();
    }
  }

  @FXML
  void deleteClient(ActionEvent event) {
    CustomAlert.showCustomAlert(
      Alert.AlertType.CONFIRMATION,
      "Question",
      "Are you sure to delete this client with code " + clientToEdit.code() + "?"
    ).ifPresent((ButtonType btn) -> {
      if (btn.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
        String result = clientService.delete(clientToEdit.code());
        CustomAlert.showCustomAlert(Alert.AlertType.INFORMATION, "Information", result);

        isModified = true;
        Stage stage = (Stage) clBtnDelete.getScene().getWindow();
        stage.close();
      }
    });
  }
}
