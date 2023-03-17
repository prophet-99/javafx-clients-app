package org.prophet99.clientsapp.data.models.dao.interfaces;

import org.prophet99.clientsapp.data.models.dao.IEntityDAO;
import org.prophet99.clientsapp.data.models.entities.Client;

import java.util.List;

public interface IClientDAO extends IEntityDAO<Client, String> {
  List<Client> findByIdOrNameOrLastName(String param);
}
