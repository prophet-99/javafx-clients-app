package org.prophet99.clientsapp.data.models.dao.implementations;

import org.prophet99.clientsapp.data.config.ConnectionConfig;
import org.prophet99.clientsapp.data.models.dao.interfaces.IClientDAO;
import org.prophet99.clientsapp.data.models.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAOImpl implements IClientDAO {
  @Override
  public String save(Client client) {
    Connection cnn = null;
    CallableStatement cStmt = null;

    try {
      cnn = ConnectionConfig.getConnection();
      cStmt = cnn.prepareCall("{ call sp_create_client(?, ?, ?, ?, ?) }");
      cStmt.setString(1, client.code());
      cStmt.setString(2, client.firstName());
      cStmt.setString(3, client.lastName());
      cStmt.setString(4, client.email());
      cStmt.setString(5, client.phone());
      cStmt.execute();

      return cStmt.getWarnings().getMessage();
    } catch (SQLException e) {
      e.printStackTrace();

      return e.getMessage();
    } finally {
      ConnectionConfig.close(cStmt);
      ConnectionConfig.close(cnn);
    }
  }

  @Override
  public String update(String code, Client client) {
    Connection cnn = null;
    CallableStatement cStmt = null;

    try {
      cnn = ConnectionConfig.getConnection();
      cStmt = cnn.prepareCall("{ call sp_create_client(?, ?, ?, ?, ?) }");
      cStmt.setString(1, code);
      cStmt.setString(2, client.firstName());
      cStmt.setString(3, client.lastName());
      cStmt.setString(4, client.email());
      cStmt.setString(5, client.phone());
      cStmt.execute();

      return cStmt.getWarnings().getMessage();
    } catch (SQLException e) {
      e.printStackTrace();

      return e.getMessage();
    } finally {
      ConnectionConfig.close(cStmt);
      ConnectionConfig.close(cnn);
    }
  }

  @Override
  public String delete(String code) {
    Connection cnn = null;
    CallableStatement cStmt = null;

    try {
      cnn = ConnectionConfig.getConnection();
      cStmt = cnn.prepareCall("{ call sp_delete_client(?) }");
      cStmt.setString(1, code);
      cStmt.execute();

      return cStmt.getWarnings().getMessage();
    } catch (SQLException e) {
      e.printStackTrace();

      return e.getMessage();
    } finally {
      ConnectionConfig.close(cStmt);
      ConnectionConfig.close(cnn);
    }
  }

  @Override
  public List<Client> getAll() {
    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Client> clientList = new ArrayList<>();

    try {
      cnn = ConnectionConfig.getConnection();
      ps = cnn.prepareStatement("select * from clients");
      rs = ps.executeQuery();
      while(rs.next()) {
        int idx = 0;
        clientList.add(new Client(
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getTimestamp(++idx).toLocalDateTime()
        ));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionConfig.close(rs);
      ConnectionConfig.close(ps);
      ConnectionConfig.close(cnn);
    }

    return clientList;
  }

  @Override
  public Optional<Client> findById(String code) {
    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Client client = null;

    try {
      cnn = ConnectionConfig.getConnection();
      ps = cnn.prepareStatement("select * from clients where code ilike ?");
      int idx = 0;
      ps.setString(++idx, code + "%");
      rs = ps.executeQuery();
      while(rs.next()) {
        idx = 0;
        client = new Client(
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getString(++idx),
                rs.getTimestamp(++idx).toLocalDateTime()
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionConfig.close(rs);
      ConnectionConfig.close(ps);
      ConnectionConfig.close(cnn);
    }

    return Optional.ofNullable(client);
  }

  @Override
  public List<Client> findByIdOrNameOrLastName(String param) {
    Connection cnn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Client> clientList = new ArrayList<>();

    try {
      cnn = ConnectionConfig.getConnection();
      ps = cnn.prepareStatement("select * from clients where code ilike ? or first_name ilike ? or last_name ilike ?");
      int idx = 0;
      ps.setString(++idx, param + "%");
      ps.setString(++idx, param + "%");
      ps.setString(++idx, param + "%");
      rs = ps.executeQuery();
      while(rs.next()) {
        idx = 0;
        clientList.add(
                new Client(
                        rs.getString(++idx),
                        rs.getString(++idx),
                        rs.getString(++idx),
                        rs.getString(++idx),
                        rs.getString(++idx),
                        rs.getTimestamp(++idx).toLocalDateTime()
                )
        );
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConnectionConfig.close(rs);
      ConnectionConfig.close(ps);
      ConnectionConfig.close(cnn);
    }

    return clientList;
  }
}
