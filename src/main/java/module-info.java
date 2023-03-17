module org.prophet99.clientsapp {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;

  opens org.prophet99.clientsapp to javafx.fxml;
  exports org.prophet99.clientsapp;
  exports org.prophet99.clientsapp.controllers;
  opens org.prophet99.clientsapp.controllers to javafx.fxml;
}