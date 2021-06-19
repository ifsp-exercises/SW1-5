import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class PageWelcomeServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    PrintWriter printWritter = response.getWriter();

    printWritter.println("<html><body>");
    printWritter.println("Welcome to servlet");
    printWritter.println("</body></html>");

    printWritter.close();
  }
}
