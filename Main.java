import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Agenda agenda = new Agenda();
    int opcao;
    do {
      renderizarMenu();
      opcao = scanner.nextInt();
      scanner.nextLine();
      switch (opcao) {
        case 1:
          listarContatos(agenda);
          break;
        case 2:
          cadastrarContato(scanner, agenda);
          break;
        case 3:
          deletarContato(scanner, agenda);
          break;
        default:
          System.err.println("Opção inválida.");
      }
    } while (opcao != 4);
    scanner.close();
  }

  private static void deletarContato(Scanner scanner, Agenda agenda) {
    System.out.println("Digite o numero do contato para DELETAR");
    int indiceDeletar = scanner.nextInt();
    scanner.nextLine();
    agenda.deletarContato(indiceDeletar);
    
  }

  private static void cadastrarContato(Scanner scanner, Agenda agenda) {
    System.out.println("Digite o nome");
    String nome = scanner.nextLine();
    System.out.println("Digite o telefone");
    String telefone = scanner.nextLine();
    var contato = new Contato(nome, telefone);
    agenda.adicionarContato(contato);
  }

  private static void renderizarMenu() {
    System.out.println("\nMenu Gerenciador de Contatos");
    System.out.println("1. Listar Contatos");
    System.out.println("2. Adicionar Contato");
    System.out.println("3. Deletar Contato");
    System.out.println("4. Encerrar Programa");
    System.out.print("Escolha uma opção: ");
  }
  private static void listarContatos(Agenda agenda) {
    System.out.println("\nLista de contatos:");
    agenda.listarContatos();
  }
}
