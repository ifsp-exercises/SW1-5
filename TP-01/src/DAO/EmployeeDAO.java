package DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Entities.EmployeeEntity;

public class EmployeeDAO {

  public EmployeeDAO() {
  }

  public static Connection getConnection() throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://db:3306/employees?useTimezone=true&serverTimezone=UTC", "root",
        "DockerMySql127!");

  }

  public static int save(EmployeeEntity e) {
    int status = 0;
    try {
      Connection con = EmployeeDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("insert into users(name, password, email, country) values (?,?,?,?)");
      ps.setString(1, e.getName());
      ps.setString(2, e.getPassword());
      ps.setString(3, e.getEmail());
      ps.setString(4, e.getCountry());

      status = ps.executeUpdate();

      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }

  public static int update(EmployeeEntity e) {
    int status = 0;
    try {
      Connection con = EmployeeDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("update users set name=?, password=?, email=?, country=? where id=?");
      ps.setString(1, e.getName());
      ps.setString(2, e.getPassword());
      ps.setString(3, e.getEmail());
      ps.setString(4, e.getCountry());
      ps.setInt(5, e.getId());

      status = ps.executeUpdate();

      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }

  public static int delete(int id) {
    int status = 0;
    try {
      Connection con = EmployeeDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("delete from users where id=?");
      ps.setInt(1, id);
      status = ps.executeUpdate();

      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }

  public static EmployeeEntity getEmployeeById(int id) {
    EmployeeEntity e = new EmployeeEntity();

    try {
      Connection con = EmployeeDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from users where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        e.setId(rs.getInt(1));
        e.setName(rs.getString(2));
        e.setPassword(rs.getString(3));
        e.setEmail(rs.getString(4));
        e.setCountry(rs.getString(5));
      }
      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return e;
  }

  public static List<EmployeeEntity> getAllEmployees() {
    List<EmployeeEntity> list = new ArrayList<EmployeeEntity>();

    try {
      Connection con = EmployeeDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from users");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        EmployeeEntity e = new EmployeeEntity();
        e.setId(rs.getInt(1));
        e.setName(rs.getString(2));
        e.setPassword(rs.getString(3));
        e.setEmail(rs.getString(4));
        e.setCountry(rs.getString(5));
        list.add(e);
      }
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
}
