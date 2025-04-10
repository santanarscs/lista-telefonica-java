package com.santana.repository;
import java.util.List;
import java.util.Optional;

import com.santana.model.Contato;

public interface ContatoRepository {
  public void cadastrar(Contato contato);
  public void deletar(Long id);
  public List<Contato> listarContatos();
  public Contato buscarPorId(Long id);
  public Optional<Contato> buscarPorNome(String nome);
  public Optional<Contato> buscarPorTelefone(String telefone);
}
