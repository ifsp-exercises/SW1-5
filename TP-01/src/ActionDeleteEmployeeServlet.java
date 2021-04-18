
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAO.EmployeeDAO;
import Entities.EmployeeEntity;

public class ActionDeleteEmployeeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public ActionDeleteEmployeeServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer id = Integer.parseInt(request.getParameter("id"));

    EmployeeEntity em = EmployeeDAO.getEmployeeById(id);
    if (em != null) {
      EmployeeDAO.delete(em.getId());
      PrintWriter out = response.getWriter();
      out.print("<body><script type=\"text/javascript\">\r\n" + "  	function redirect(){  		\r\n"
          + "  		alert(\"employee successfully removed!\");\r\n"
          + "  		window.location.replace(\"http://localhost:8080/TP-01/ReadServlet\");\r\n" + "  		\r\n"
          + "  	}\r\n" + "  	redirect()\r\n" + "  </script></body>");
    }
    response.getWriter().append("Served at: " + id + " aa ").append(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.getWriter().append("Served at:  POST ").append(request.getContextPath());
  }
}
