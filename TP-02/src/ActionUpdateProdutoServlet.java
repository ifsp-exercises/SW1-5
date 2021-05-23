import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import DAO.ProdutoDAO;
import Entities.ProdutoEntity;
import Entities.ResponseResult;

public class ActionUpdateProdutoServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionUpdateProdutoServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String requestBody = IOUtils.toString(request.getReader());
      ProdutoEntity produto = this._jsonHandler.fromJson(requestBody, ProdutoEntity.class);

      ProdutoEntity produtoFound = ProdutoDAO.getProdutoById(produto.getID());
      boolean produtoExists = produtoFound != null && produtoFound.getID() != 0;

      String message;
      boolean updatedSuccessfully = false;

      if (!produtoExists) {
        message = "Produto not found.";
      } else {
        updatedSuccessfully = ProdutoDAO.update(produto) > 0;

        if (updatedSuccessfully)
          message = "Produto updated successfully.";
        else
          message = "An error occurred while updating produto.";
      }

      boolean everythingIsOk = produtoExists && updatedSuccessfully;

      result = new ResponseResult(everythingIsOk, message, new Object());
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
