package org.prophet99.clientsapp.data.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConnectionConfig {
  private static String jdbcURL;
  private static String username;
  private static String password;
  private static String propertiesURL = "config.properties";

  private static Properties loadProperties(String file) {
    Properties props = null;
    try(FileInputStream fileInputStream = new FileInputStream(file)) {
      props = new Properties();
      ResourceBundle bundle = new PropertyResourceBundle(fileInputStream);
      Enumeration bundleKeys = bundle.getKeys();
      String key;
      while(bundleKeys.hasMoreElements()) {
        key = (String) bundleKeys.nextElement();
        props.put(key, bundle.getString(key));
      }
      jdbcURL = props.getProperty("jdbc_url");
      username = props.getProperty("db_user");
      password = props.getProperty("db_password");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return props;
  }

  public static synchronized Connection getConnection() throws SQLException {
    loadProperties(Paths.get(".", propertiesURL).toString());
    Properties scopeProps = new Properties();
    scopeProps.put("user", username);
    scopeProps.put("password", password);
    scopeProps.put("escapeSyntaxCallMode", "callIfNoReturn");
    Connection connection = DriverManager.getConnection(jdbcURL, scopeProps);

    return connection;
  }

  public static void close(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(PreparedStatement pStmt){
    if(pStmt != null){
      try {
        pStmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(CallableStatement cStmt){
    if(cStmt != null){
      try {
        cStmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void close(Connection cnn){
    if(cnn != null){
      try {
        cnn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
