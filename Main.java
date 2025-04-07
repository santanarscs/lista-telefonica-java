import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  static List<Contato> contatos = new ArrayList<>();
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
      renderizarMenu();
      opcao = scanner.nextInt();
      scanner.nextLine();
      switch (opcao) {
        case 1:
          listarContatos();
          break;
        case 2:
          cadastrarContato(scanner);
          break;
        case 3:
          deletarContato(scanner);
          break;
        default:
          System.err.println("Opção inválida.");
      }
    } while (opcao != 4);
    scanner.close();
  }

  private static void deletarContato(Scanner scanner) {
    System.out.println("Digite o numero do contato para DELETAR");
    int indiceDeletar = scanner.nextInt();
    scanner.nextLine();
    if (indiceDeletar > 0 && indiceDeletar <= contatos.size()) {
      contatos.remove(indiceDeletar - 1);
      System.out.println("Contato deletado com sucesso.");
    } else {
      System.out.println("Número de contato inválido.");
    }
  }

  private static void cadastrarContato(Scanner scanner) {
    System.out.println("Digite o nome");
    String nome = scanner.nextLine();
    System.out.println("Digite o telefone");
    String telefone = scanner.nextLine();
    var contato = new Contato(nome, telefone);
    contatos.add(contato);
  }

  private static void renderizarMenu() {
    System.out.println("\nMenu Gerenciador de Contatos");
    System.out.println("1. Listar Contatos");
    System.out.println("2. Adicionar Contato");
    System.out.println("3. Deletar Contato");
    System.out.println("4. Encerrar Programa");
    System.out.print("Escolha uma opção: ");
  }
  private static void listarContatos() {
    System.out.println("\nLista de contatos:");
    if (contatos.isEmpty()) {
      System.out.println("Nenhum contato cadastrado.");
    } else {
      for (int i = 0; i < contatos.size(); i++) {
        System.out.println((i + 1) + " - " + contatos.get(i).getNome() + " " + contatos.get(i).getTelefone());
      }
    }
  }
}
