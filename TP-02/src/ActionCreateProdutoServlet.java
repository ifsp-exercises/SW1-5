import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import Entities.*;
import DAO.ProdutoDAO;

public class ActionCreateProdutoServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionCreateProdutoServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String requestBody = IOUtils.toString(request.getReader());
      ProdutoEntity produto = this._jsonHandler.fromJson(requestBody, ProdutoEntity.class);

      boolean savedSuccessfully = ProdutoDAO.save(produto) > 0;

      String message;

      if (savedSuccessfully)
        message = "New produto added successfully.";
      else
        message = "An error occurred while saving produto.";

      result = new ResponseResult(savedSuccessfully, message, new Object());
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
