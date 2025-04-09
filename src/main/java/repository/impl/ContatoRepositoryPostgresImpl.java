package src.main.java.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.main.java.model.Contato;
import src.main.java.repository.ContatoRepository;
import src.main.java.singleton.DatabaseConnection;

public class ContatoRepositoryPostgresImpl implements ContatoRepository {

  @Override
  public void cadastrar(Contato contato) {
    String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, contato.getNome());
      stmt.setString(2, contato.getTelefone());
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deletar(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deletar'");
  }

  @Override
  public List<Contato> listarContatos() {
    List<Contato> contatos = new ArrayList<>();
    String sql = "SELECT * FROM contatos";

    try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Contato contato = new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone"));
        contatos.add(contato);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return contatos;
  }

  @Override
  public Contato buscarPorId(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
  }

  @Override
  public Optional<Contato> buscarPorNome(String nome) {
    Optional<Contato> contato =  Optional.empty();
    StringBuilder query = new StringBuilder("select * from contatos where nome like ?");
    String criterioPesquisa = "%" + nome + "%";
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(query.toString());
      stmt.setString(1, criterioPesquisa); 
      ResultSet rs = stmt.executeQuery();
      if(rs.getFetchSize() != 0) {
        while (rs.next()) {
          contato = Optional.of(new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone")));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contato;
  }

  @Override
  public Optional<Contato> buscarPorTelefone(String telefone) {
    Optional<Contato> contato =  Optional.empty();
    StringBuilder query = new StringBuilder("select * from contatos where telefone like ?");
    String criterioPesquisa = "%" + telefone + "%";
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement stmt = connection.prepareStatement(query.toString());
      stmt.setString(1, criterioPesquisa); 

      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        contato = Optional.of(new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return contato;
  }
}
