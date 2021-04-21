import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class PageListEmployeeServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("./static/pages/employees.html");

    view.forward(request, response);
  }
}
