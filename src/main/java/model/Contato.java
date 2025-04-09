package src.main.java.model;
public class Contato {
  private Long id;
  private String nome;
  private String telefone;
  
  public Contato() {}
  
  public Contato(String nome, String telefone) {
    this.nome = nome;
    this.telefone = telefone;
  }
  public Contato(Long id, String nome, String telefone) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
  }
  public Long getId() {
    return id;
  }
  
  public String getNome() {
    return nome;
  }
  public String getTelefone() {
    return telefone;
  }
  
}
