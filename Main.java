import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    List<Contato> contatos = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int opcao;
    do {
      System.out.println("\nMenu Gerenciador de Contatos");
      System.out.println("1. Listar Contatos");
      System.out.println("2. Adicionar Contato");
      System.out.println("3. Deletar Contato");
      System.out.println("4. Encerrar Programa");
      System.out.print("Escolha uma opção: ");
      opcao = scanner.nextInt();
      scanner.nextLine();
      switch (opcao) {
        case 1:
          System.out.println("\nLista de contatos:");
          if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
          } else {
            for (int i = 0; i < contatos.size(); i++) {
              System.out.println((i + 1) + " - " + contatos.get(i).getNome() + " " + contatos.get(i).getTelefone());
            }
          }
          break;
        case 2:
          System.out.println("Digite o nome");
          String nome = scanner.nextLine();
          System.out.println("Digite o telefone");
          String telefone = scanner.nextLine();
          var contato = new Contato(nome, telefone);
          contatos.add(contato);
          break;
        case 3:
          System.out.println("Digite o numero do contato para DELETAR");
          int indiceDeletar = scanner.nextInt();
          scanner.nextLine();
          if (indiceDeletar > 0 && indiceDeletar <= contatos.size()) {
            contatos.remove(indiceDeletar - 1);
            System.out.println("Contato deletado com sucesso.");
          } else {
            System.out.println("Número de contato inválido.");
          }
          break;

        default:
          System.err.println("Opção inválida.");
      }
    } while (opcao != 4);
    scanner.close();
  }
}
