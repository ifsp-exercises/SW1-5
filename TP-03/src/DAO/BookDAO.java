package DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Entities.BookEntity;

public class BookDAO {

  public BookDAO() {
  }

  public static Connection getConnection() throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://db:3306/book_store?useTimezone=true&serverTimezone=UTC",
        "root", "DockerMySql127!");

  }

  public static int save(BookEntity e) {
    int status = 0;
    try {
      Connection con = BookDAO.getConnection();

      PreparedStatement ps = con.prepareStatement(
        "insert into books("
          + "title,"
          + "author,"
          + "price"
        + ") values (?,?,?)"
      );

      ps.setString(1, e.getTittle());
      ps.setString(2, e.getAuthor());
      ps.setDouble(3, e.getPrice());

      status = ps.executeUpdate();

      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }

  public static int update(BookEntity e) {
    int status = 0;
    try {
      Connection con = BookDAO.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "update books set title=?, author=?, price=? where id=?");
      ps.setString(1, e.getTittle());
      ps.setString(2, e.getAuthor());
      ps.setDouble(3, e.getPrice());
      ps.setDouble(4, e.getID());

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
      Connection con = BookDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("delete from books where id=?");
      ps.setInt(1, id);
      status = ps.executeUpdate();

      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }

  public static BookEntity getbookById(int id) {
    BookEntity e = new BookEntity();

    try {
      Connection con = BookDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from books where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        e.setID(rs.getInt(1));
        e.setTittle(rs.getString(2));
        e.setAuthor(rs.getString(3));
        e.setPrice(rs.getDouble(4));
      }
      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return e;
  }

  public static List<BookEntity> getAllbooks() {
    List<BookEntity> list = new ArrayList<BookEntity>();

    try {
      Connection con = BookDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from books");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        BookEntity e = new BookEntity();

        e.setID(rs.getInt(1));
        e.setTittle(rs.getString(2));
        e.setAuthor(rs.getString(3));
        e.setPrice(rs.getDouble(4));

        list.add(e);
      }
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
}
