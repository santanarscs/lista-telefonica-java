package src.service;
import java.util.List;

import src.model.Contato;
import src.repository.ContatoRepository;

public class AgendaService {

  private ContatoRepository repository;

  public AgendaService(ContatoRepository repository) {
    this.repository = repository;
  }

  public void adicionarContato(Contato contato) {
    var mesmoNumeroExiste = repository.buscarPorTelefone(contato.getTelefone());
    var mesmoNomeExiste = repository.buscarPorNome(contato.getNome());

    if (mesmoNumeroExiste.isPresent()) {
      System.out.println("Já existe um contato com este numero");
    } else if (mesmoNomeExiste.isPresent()) {
      System.out.println("Já existe um contato com este nome");
    } else {
      repository.cadastrar(contato);
    }
  }

  public void listarContatos() {
    List<Contato> contatos = repository.listarContatos();
    if (contatos.isEmpty()) {
      System.out.println("Nenhum contato cadastrado.");
    } else {
      for(Contato contato : contatos) {
        System.out.println(contato.getId() + " - " + contato.getNome() + " " + contato.getTelefone());
      }
    }
  }

  public void deletarContato(Long id) {
    repository.deletar(id);
    // var contatos = repository.listarContatos();
    // if (indiceDeletar > 0 && indiceDeletar <= contatos.size()) {
    //   contatos.remove(indiceDeletar - 1);
    //   System.out.println("Contato deletado com sucesso.");
    // } else {
    //   System.out.println("Número de contato inválido.");
    // }
  }
}
