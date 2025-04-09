package src.main.java.repository.impl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import src.main.java.model.Contato;
import src.main.java.repository.ContatoRepository;

public class ContatoRepositoryFileImpl implements ContatoRepository {
  private final String caminhoArquivo;
  private static final long VALOR_MAXIMO = 999;
  private static final Random random = new Random();

  public ContatoRepositoryFileImpl(){
    caminhoArquivo = getClass().getClassLoader().getResource("contatos.txt").getFile();
  }

  public void cadastrar(Contato contato) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
      long numeroAleatorio = random.nextLong() & VALOR_MAXIMO;
      writer.write(numeroAleatorio + ";" + contato.getNome() + ";" + contato.getTelefone());
      writer.newLine();
      System.out.println("Contato adicionado com sucesso.");
    } catch (IOException e) {
      System.out.println("Erro ao adicionar contato: " + e.getMessage());
    }
  }

  public List<Contato> listarContatos() {
    List<Contato> contatos = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
      String linha;
      while ((linha = reader.readLine()) != null) {
        String[] partes = linha.split(";");
        if (partes.length == 3) {
          Contato contato = new Contato(Long.parseLong(partes[0]), partes[1], partes[2]);
          contatos.add(contato);
        }
      }
    } catch (IOException e) {
      System.out.println("Erro ao ler contatos: " + e.getMessage());
    }
    return contatos;
  }

  @Override
  public void deletar(Long id) {
    List<Contato> contatos = listarContatos();
    contatos.removeIf(contato -> contato.getId().equals(id));

    if (contatos.size() < listarContatos().size()) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Contato contato : contatos) {
                writer.write(contato.getId() + ";" + contato.getNome() + ";" + contato.getTelefone());
                writer.newLine();
            }
            System.out.println("Contato deletado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao deletar contato: " + e.getMessage());
        }
    } else {
        System.out.println("Contato não encontrado.");
    }
  }

  @Override
  public Contato buscarPorId(Long id) {
    var contatos = listarContatos();
    return contatos.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
  }

  @Override
  public Optional<Contato> buscarPorNome(String nome) {
    var contatos = listarContatos();
    return contatos.stream().filter(c -> nome.equals(c.getNome())).findFirst();
  }

  @Override
  public Optional<Contato> buscarPorTelefone(String telefone) {
    var contatos = listarContatos();
    return contatos.stream().filter(c -> telefone.equals(c.getTelefone())).findFirst();
  }
}
