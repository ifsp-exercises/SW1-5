import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

import DAO.BookDAO;
import Entities.BookEntity;
import Entities.ResponseResult;

public class ActionDeleteBookServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionDeleteBookServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String message;

      Integer bookId = Integer.parseInt(request.getParameter("id"));

      BookEntity book = BookDAO.getbookById(bookId);
      boolean bookExists = book != null && book.getID() != 0;

      if (!bookExists) {
        message = "book not found.";
      } else {
        BookDAO.delete(book.getID());

        message = "book successfully deleted.";
      }

      result = new ResponseResult(bookExists, message, null);
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
