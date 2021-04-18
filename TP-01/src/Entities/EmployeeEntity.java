package Entities;

public class EmployeeEntity {
  private int id;
  private String name, password, email, country;

  public EmployeeEntity(int id, String name, String email, String password, String country) {
    super();

    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.country = country;
  }

  public EmployeeEntity() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
