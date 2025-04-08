import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agenda {

  private List<Contato> contatos = new ArrayList<>();

  public List<Contato> getContatos() {
    return contatos;
  }
  public void adicionarContato(Contato contato) {
    // validar se existe um contato com o mesmo numero
    var mesmoNumeroExiste = contatos
        .stream()
        .filter(c -> contato.getTelefone().equals(c.getTelefone()))
        .findFirst()
        .orElse(null);
    var mesmoNomeExiste = contatos
        .stream()
        .filter(c -> contato.getNome().equals(c.getNome()))
        .findFirst()
        .orElse(null);

    if (Objects.nonNull(mesmoNumeroExiste)) {
      System.out.println("Já existe um contato com este numero");
    }else if(Objects.nonNull(mesmoNomeExiste)) {
      System.out.println("Já existe um contato com este nome");
    } 
    else {
      contatos.add(contato);
    }
  }

  public void listarContatos() {
    if (contatos.isEmpty()) {
      System.out.println("Nenhum contato cadastrado.");
    } else {
      for (int i = 0; i < contatos.size(); i++) {
        System.out.println((i + 1) + " - " + contatos.get(i).getNome() + " " + contatos.get(i).getTelefone());
      }
    }
  }

  public void deletarContato(int indiceDeletar) {
    if (indiceDeletar > 0 && indiceDeletar <= contatos.size()) {
      contatos.remove(indiceDeletar - 1);
      System.out.println("Contato deletado com sucesso.");
    } else {
      System.out.println("Número de contato inválido.");
    }
  }
}
