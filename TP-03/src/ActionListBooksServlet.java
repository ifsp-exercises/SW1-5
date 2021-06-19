import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import DAO.BookDAO;
import Entities.BookEntity;
import Entities.ResponseResult;

public class ActionListBooksServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionListBooksServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      List<BookEntity> books = BookDAO.getAllbooks();

      result = new ResponseResult(true, "", books);
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
