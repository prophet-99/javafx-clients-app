package org.prophet99.clientsapp.data.services.interfaces;

import org.prophet99.clientsapp.data.models.entities.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {
  String save(Client client);
  String update(String code, Client client);
  String delete(String code);
  Optional<Client> findById(String code);
  List<Client> getAll();
  List<Client> findByIdOrNameOrLastName(String param);
}
