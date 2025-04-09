package src.main.java.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5432/agenda";
  private static final String USER = "docker";
  private static final String PASSWORD = "docker";

  private DatabaseConnection() {}

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException e) {
      throw new SQLException("Driver do PostgreSQL não encontrado.", e);
    }
  }
}
