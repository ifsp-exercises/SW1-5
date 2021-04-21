import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class PageHomeServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    RequestDispatcher view = request.getRequestDispatcher("./static/pages/home.html");

    view.forward(request, response);
  }
}
