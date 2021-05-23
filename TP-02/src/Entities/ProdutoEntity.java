package Entities;

public class ProdutoEntity {
  private int id;
  private String nome;
  private String descricao;
  private double unidadeCompra;
  private double qtdPrevistoMes;
  private double precoMaxComprado;

  public int getID() {
    return id;
  }

  public void setID(int value) {
    this.id = value;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String value) {
    this.nome = value;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String value) {
    this.descricao = value;
  }

  public double getUnidadeCompra() {
    return unidadeCompra;
  }

  public void setUnidadeCompra(double value) {
    this.unidadeCompra = value;
  }

  public double getQtdPrevistoMes() {
    return qtdPrevistoMes;
  }

  public void setQtdPrevistoMes(double value) {
    this.qtdPrevistoMes = value;
  }

  public double getPrecoMaxComprado() {
    return precoMaxComprado;
  }

  public void setPrecoMaxComprado(double value) {
    this.precoMaxComprado = value;
  }
}
