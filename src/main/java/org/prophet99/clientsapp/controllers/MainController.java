package org.prophet99.clientsapp.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.prophet99.clientsapp.MainApp;
import org.prophet99.clientsapp.data.models.entities.Client;
import org.prophet99.clientsapp.data.services.implementations.ClientServiceImpl;
import org.prophet99.clientsapp.data.services.interfaces.IClientService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MainController implements Initializable {
  // LOGIC ATTRIBUTES
  private IClientService clientService = new ClientServiceImpl();
  // JFX ATTRIBUTES
  @FXML
  private Button clBtnManage;
  @FXML
  private TextField clTxtSearch;
  @FXML
  private Button clBtnSearch;
  @FXML
  private TableColumn<Client, String> clColumnCode;
  @FXML
  private TableColumn<Client, String> clColumnFirstName;
  @FXML
  private TableColumn<Client, String> clColumnLastName;
  @FXML
  private TableColumn<Client, String> clColumnEmail;
  @FXML
  private TableColumn<Client, String> clColumnPhone;
  @FXML
  private TableColumn<Client, LocalDateTime> clColumnCreatedAt;
  @FXML
  private TableView<Client> clTable;
  private ObservableList<Client> clientObservableList = FXCollections.observableArrayList();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // ADD DATA TO TABLE
    handleLoadTable();
    // TABLE EVENTS
    handleAddDblClickEventToTable();
  }

  private void handleLoadTable() {
    clColumnCode.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().code()));
    clColumnFirstName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().firstName()));
    clColumnLastName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().lastName()));
    clColumnEmail.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().email()));
    clColumnPhone.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().phone()));
    clColumnCreatedAt.setCellValueFactory(
      p -> new SimpleObjectProperty(
        p.getValue().registrationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm"))
      )
    );

    clientObservableList.addAll(clientService.getAll());
    clTable.setItems(clientObservableList);
  }

  private void handleAddDblClickEventToTable() {
    clTable.setRowFactory(tableView -> {
      TableRow<Client> row = new TableRow<>();
      row.setOnMouseClicked(evt -> {
        if (evt.getClickCount() == 2 && (!row.isEmpty())) {
          Client rowData = row.getItem();
          // SHOW SCENE TO DELETE OR EDIT CLIENT
          openEditOrDeleteClientModal(rowData);
        }
      });
      return row;
    });
  }

  private void openEditOrDeleteClientModal(Client client) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/edit-or-delete-client-view.fxml"));
      Parent root = fxmlLoader.load();
      EditOrDeleteClientController controller = fxmlLoader.getController();
      controller.isModified = false;
      controller.loadClient(client);

      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();

      if(controller.isModified) {
        clientObservableList.clear();
        clientObservableList.addAll(clientService.getAll());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void openCreateClientModal(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("views/create-client-view.fxml"));
      Parent root = fxmlLoader.load();
      CreateClientController controller = fxmlLoader.getController();
      controller.isCreated = false;

      Scene scene = new Scene(root);
      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setScene(scene);
      stage.showAndWait();

      if (controller.isCreated) {
        clientObservableList.clear();
        clientObservableList.addAll(clientService.getAll());
//        clTable.refresh();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void searchClientFromKeyEvent(KeyEvent event) {
    if (event.getCode().equals(KeyCode.ENTER)) {
      handleSearchClient();
    }
  }

  @FXML
  void searchClient(ActionEvent event) {
    handleSearchClient();
  }

  private void handleSearchClient() {
    String param = clTxtSearch.getText().trim();
    if (param.equals("")) {
      clientObservableList.clear();
      clientObservableList.addAll(clientService.getAll());
    } else {
      clientObservableList.clear();
      clientObservableList.addAll(clientService.findByIdOrNameOrLastName(param));
    }
  }
}