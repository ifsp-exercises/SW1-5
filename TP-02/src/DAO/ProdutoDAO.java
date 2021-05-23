package DAO;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import Entities.ProdutoEntity;

public class ProdutoDAO {

  public ProdutoDAO() {
  }

  public static Connection getConnection() throws ClassNotFoundException, SQLException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection("jdbc:mysql://db:3306/gerenciador_produtos?useTimezone=true&serverTimezone=UTC",
        "root", "DockerMySql127!");

  }

  public static int save(ProdutoEntity e) {
    int status = 0;
    try {
      Connection con = ProdutoDAO.getConnection();

      PreparedStatement ps = con.prepareStatement(
        "insert into produtos("
          + "nome,"
          + "descricao,"
          + "unidadeCompra,"
          + "qtdPrevistoMes,"
          + "precoMaxComprado"
        + ") values (?,?,?,?,?)"
      );

      ps.setString(1, e.getNome());
      ps.setString(2, e.getDescricao());
      ps.setDouble(3, e.getUnidadeCompra());
      ps.setDouble(4, e.getQtdPrevistoMes());
      ps.setDouble(5, e.getPrecoMaxComprado());

      status = ps.executeUpdate();

      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return status;
  }

  public static int update(ProdutoEntity e) {
    int status = 0;
    try {
      Connection con = ProdutoDAO.getConnection();
      PreparedStatement ps = con.prepareStatement(
          "update produtos set nome=?, descricao=?, unidadeCompra=?, qtdPrevistoMes=?, precoMaxComprado=? where id=?");
      ps.setString(1, e.getNome());
      ps.setString(2, e.getDescricao());
      ps.setDouble(3, e.getUnidadeCompra());
      ps.setDouble(4, e.getQtdPrevistoMes());
      ps.setDouble(5, e.getPrecoMaxComprado());
      ps.setDouble(6, e.getID());

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
      Connection con = ProdutoDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("delete from produtos where id=?");
      ps.setInt(1, id);
      status = ps.executeUpdate();

      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return status;
  }

  public static ProdutoEntity getProdutoById(int id) {
    ProdutoEntity e = new ProdutoEntity();

    try {
      Connection con = ProdutoDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from produtos where id=?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        e.setID(rs.getInt(1));
        e.setNome(rs.getString(2));
        e.setDescricao(rs.getString(3));
        e.setUnidadeCompra(rs.getDouble(4));
        e.setQtdPrevistoMes(rs.getDouble(5));
        e.setPrecoMaxComprado(rs.getDouble(6));
      }
      con.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return e;
  }

  public static List<ProdutoEntity> getAllProdutos() {
    List<ProdutoEntity> list = new ArrayList<ProdutoEntity>();

    try {
      Connection con = ProdutoDAO.getConnection();
      PreparedStatement ps = con.prepareStatement("select * from produtos");
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        ProdutoEntity e = new ProdutoEntity();

        e.setID(rs.getInt(1));
        e.setNome(rs.getString(2));
        e.setDescricao(rs.getString(3));
        e.setUnidadeCompra(rs.getDouble(4));
        e.setQtdPrevistoMes(rs.getDouble(5));
        e.setPrecoMaxComprado(rs.getDouble(6));

        list.add(e);
      }
      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }
}
