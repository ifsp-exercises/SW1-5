import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

import DAO.ProdutoDAO;
import Entities.ProdutoEntity;
import Entities.ResponseResult;

public class ActionDeleteProdutoServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionDeleteProdutoServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String message;

      Integer produtoId = Integer.parseInt(request.getParameter("id"));

      ProdutoEntity produto = ProdutoDAO.getProdutoById(produtoId);
      boolean produtoExists = produto != null && produto.getID() != 0;

      if (!produtoExists) {
        message = "Produto not found.";
      } else {
        ProdutoDAO.delete(produto.getID());

        message = "Produto successfully deleted.";
      }

      result = new ResponseResult(produtoExists, message, null);
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
