package Entities;

public class BookEntity {
  private int id;

  private String title;
  private String author;
  private Double price;

  public int getID() {
    return id;
  }

  public void setID(int value) {
    this.id = value;
  }

  public String getTittle() {
    return title;
  }

  public void setTittle(String value) {
    this.title = value;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String value) {
    this.author = value;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double value) {
    this.price = value;
  }
}
