import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import DAO.BookDAO;
import Entities.BookEntity;
import Entities.ResponseResult;

public class ActionUpdateBookServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionUpdateBookServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String requestBody = IOUtils.toString(request.getReader());
      BookEntity book = this._jsonHandler.fromJson(requestBody, BookEntity.class);

      BookEntity bookFound = BookDAO.getbookById(book.getID());
      boolean bookExists = bookFound != null && bookFound.getID() != 0;

      String message;
      boolean updatedSuccessfully = false;

      if (!bookExists) {
        message = "book not found.";
      } else {
        updatedSuccessfully = BookDAO.update(book) > 0;

        if (updatedSuccessfully)
          message = "book updated successfully.";
        else
          message = "An error occurred while updating book.";
      }

      boolean everythingIsOk = bookExists && updatedSuccessfully;

      result = new ResponseResult(everythingIsOk, message, new Object());
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
