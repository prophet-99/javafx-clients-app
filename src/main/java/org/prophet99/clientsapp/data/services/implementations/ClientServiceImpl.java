package org.prophet99.clientsapp.data.services.implementations;

import org.prophet99.clientsapp.data.models.dao.implementations.ClientDAOImpl;
import org.prophet99.clientsapp.data.models.dao.interfaces.IClientDAO;
import org.prophet99.clientsapp.data.models.entities.Client;
import org.prophet99.clientsapp.data.services.interfaces.IClientService;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements IClientService {
  private IClientDAO clientDAO = new ClientDAOImpl();

  @Override
  public String save(Client client) {
    return clientDAO.save(client);
  }

  @Override
  public String update(String code, Client client) {
    return clientDAO.update(code, client);
  }

  @Override
  public String delete(String code) {
    return clientDAO.delete(code);
  }

  @Override
  public Optional<Client> findById(String code) {
    return clientDAO.findById(code);
  }

  @Override
  public List<Client> getAll() {
    return clientDAO.getAll();
  }

  @Override
  public List<Client> findByIdOrNameOrLastName(String param) {
    return clientDAO.findByIdOrNameOrLastName(param);
  }
}
