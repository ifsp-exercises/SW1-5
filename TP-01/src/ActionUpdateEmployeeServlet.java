import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import DAO.EmployeeDAO;
import Entities.EmployeeEntity;

public class ActionUpdateEmployeeServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static Integer ids = 0;

  public ActionUpdateEmployeeServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Integer id = Integer.parseInt(request.getParameter("id"));
    ids = id;
    EmployeeEntity emps = EmployeeDAO.getEmployeeById(id);

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "  <head>\r\n" + "    <meta charset=\"UTF-8\" />\r\n"
        + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
        + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + "\r\n"
        + "    <title>SWI | 5 - TP 01 - Home</title>\r\n" + "\r\n"
        + "    <link rel=\"stylesheet\" href=\"style.css\" />\r\n"
        + "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" />\r\n" + "    <link\r\n"
        + "      href=\"https://fonts.googleapis.com/css2?family=Raleway:wght@100;400;700&display=swap\"\r\n"
        + "      rel=\"stylesheet\"\r\n" + "    />\r\n" + "  </head>\r\n" + "  <body>\r\n"
        + "    <main class=\"container\">\r\n" + "      <h1>Update employee</h1>\r\n" + "\r\n"
        + "      <form class=\"form\" action=\"/TP-01/UpdateEmployeeServlet\" method=\"POST\">\r\n"
        + "        <div class=\"input-container\">\r\n" + "          <svg\r\n"
        + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n" + "            fill=\"none\"\r\n"
        + "            stroke=\"currentColor\"\r\n" + "            stroke-width=\"2\"\r\n"
        + "            stroke-linecap=\"round\"\r\n" + "            stroke-linejoin=\"round\"\r\n"
        + "            class=\"feather feather-user\"\r\n" + "          >\r\n"
        + "            <path d=\"M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2\"></path>\r\n"
        + "            <circle cx=\"12\" cy=\"7\" r=\"4\"></circle>\r\n" + "          </svg>\r\n" + "\r\n"
        + "          <input type=\"text\" name=\"name\" placeholder=\"Name\" value=\"" + emps.getName() + "\" />\r\n"
        + "        </div>\r\n" + "\r\n" + "        <div class=\"input-container\">\r\n" + "          <svg\r\n"
        + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n"
        + "            fill=\"transparent\"\r\n" + "            stroke=\"%23dae8eb\"\r\n"
        + "            stroke-width=\"2\"\r\n" + "            class=\"feather feather-at-sign\"\r\n" + "          >\r\n"
        + "            <circle cx=\"12\" cy=\"12\" r=\"4\"></circle>\r\n"
        + "            <path d=\"M16 8v5a3 3 0 0 0 6 0v-1a10 10 0 1 0-3.92 7.94\"></path>\r\n" + "          </svg>\r\n"
        + "\r\n" + "          <input type=\"email\" name=\"email\" placeholder=\"E-mail\" value=\"" + emps.getEmail()
        + "\" />\r\n" + "        </div>\r\n" + "\r\n"
        + "        <div class=\"input-container\" data-error=\"false\">\r\n" + "          <svg\r\n"
        + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n" + "            fill=\"none\"\r\n"
        + "            stroke=\"currentColor\"\r\n" + "            stroke-width=\"2\"\r\n"
        + "            stroke-linecap=\"round\"\r\n" + "            stroke-linejoin=\"round\"\r\n"
        + "            class=\"feather feather-lock\"\r\n" + "          >\r\n"
        + "            <rect x=\"3\" y=\"11\" width=\"18\" height=\"11\" rx=\"2\" ry=\"2\"></rect>\r\n"
        + "            <path d=\"M7 11V7a5 5 0 0 1 10 0v4\"></path>\r\n" + "          </svg>\r\n" + "\r\n"
        + "          <input type=\"password\" name=\"password\" placeholder=\"Password\" value=\"" + emps.getPassword()
        + "\" />\r\n" + "        </div>\r\n" + "\r\n"
        + "        <div class=\"input-container\" data-error=\"false\">\r\n" + "          <svg\r\n"
        + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n" + "            fill=\"none\"\r\n"
        + "            stroke=\"currentColor\"\r\n" + "            stroke-width=\"2\"\r\n"
        + "            stroke-linecap=\"round\"\r\n" + "            stroke-linejoin=\"round\"\r\n"
        + "            class=\"feather feather-map-pin\"\r\n" + "          >\r\n"
        + "            <path d=\"M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z\"></path>\r\n"
        + "            <circle cx=\"12\" cy=\"10\" r=\"3\"></circle>\r\n" + "          </svg>\r\n" + "\r\n"
        + "          <select name=\"country\" style=\"background: #25273E;\">" + "<option value=\"Brasil\" selected > "
        + emps.getCountry() + " </option>\r\n" + "	<option value=\"Brasil\" > Brasil </option>\r\n" + "<hr />"
        + "				<option value=\"Brunei\">Brunei</option>\r\n"
        + "				<option value=\"Bulgária\">Bulgária</option>\r\n"
        + "				<option value=\"Burkina Fasso\">Burkina Fasso</option>\r\n"
        + "				<option value=\"botão\">botão</option>\r\n"
        + "				<option value=\"Cabo Verde\">Cabo Verde</option>\r\n"
        + "				<option value=\"Camarões\">Camarões</option>\r\n"
        + "				<option value=\"Camboja\">Camboja</option>\r\n"
        + "				<option value=\"Canadá\">Canadá</option>\r\n"
        + "				<option value=\"Cazaquistão\">Cazaquistão</option>\r\n"
        + "				<option value=\"Chade\">Chade</option>\r\n"
        + "				<option value=\"Chile\">Chile</option>\r\n"
        + "				<option value=\"China\">China</option>\r\n"
        + "				<option value=\"Cidade do Vaticano\">Cidade do Vaticano</option>\r\n"
        + "				<option value=\"Colômbia\">Colômbia</option>\r\n"
        + "				<option value=\"Congo\">Congo</option>" + "</select>\r\n" + "        </div>\r\n" + "\r\n"
        + "        <div class=\"form-footer\">\r\n"
        + "          <button type=\"submit\" class=\"primary\"  style=\"width:563px;\" >Confirm</button>\r\n"
        + "        </div>\r\n" + "      </form>\r\n" + "    </main>\r\n" + "\r\n" + "    <nav class=\"navbar\">\r\n"
        + "      <a\r\n" + "        title=\"Show me the code 🙃\"\r\n"
        + "        href=\"https://github.com/angeloevangelista/SW1-5\"\r\n" + "        target=\"_blank\"\r\n"
        + "        rel=\"noopener noreferrer\"\r\n" + "      >\r\n" + "        <div class=\"github-logo\"></div>\r\n"
        + "      </a>\r\n" + "\r\n" + "      <div class=\"navbar-bottom-group\">\r\n" + " \r\n" + "\r\n"
        + "        <a href=\"http://localhost:8080/TP-01/ReadServlet\">\r\n" + "          <svg\r\n"
        + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n" + "            fill=\"none\"\r\n"
        + "            stroke=\"currentColor\"\r\n" + "            stroke-width=\"2\"\r\n"
        + "            stroke-linecap=\"round\"\r\n" + "            stroke-linejoin=\"round\"\r\n"
        + "            class=\"feather feather-list\"\r\n" + "          >\r\n"
        + "            <line x1=\"8\" y1=\"6\" x2=\"21\" y2=\"6\"></line>\r\n"
        + "            <line x1=\"8\" y1=\"12\" x2=\"21\" y2=\"12\"></line>\r\n"
        + "            <line x1=\"8\" y1=\"18\" x2=\"21\" y2=\"18\"></line>\r\n"
        + "            <line x1=\"3\" y1=\"6\" x2=\"3.01\" y2=\"6\"></line>\r\n"
        + "            <line x1=\"3\" y1=\"12\" x2=\"3.01\" y2=\"12\"></line>\r\n"
        + "            <line x1=\"3\" y1=\"18\" x2=\"3.01\" y2=\"18\"></line>\r\n" + "          </svg>\r\n"
        + "        </a>\r\n" + "        <a href=\"http://localhost:8080/TP-01/authors.html\" >\r\n"
        + "          <svg\r\n" + "            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + "            width=\"24\"\r\n"
        + "            height=\"24\"\r\n" + "            viewBox=\"0 0 24 24\"\r\n" + "            fill=\"none\"\r\n"
        + "            stroke=\"currentColor\"\r\n" + "            stroke-width=\"2\"\r\n"
        + "            stroke-linecap=\"round\"\r\n" + "            stroke-linejoin=\"round\"\r\n"
        + "            class=\"feather feather-star\"\r\n" + "          >\r\n" + "            <polygon\r\n"
        + "              points=\"12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2\"\r\n"
        + "            ></polygon>\r\n" + "          </svg>\r\n" + "        </a>\r\n" + "      </div>\r\n"
        + "    </nav>\r\n" + "\r\n" + "    <script src=\"./assets/scripts/global.js\"></script>\r\n"
        + "    <script src=\"./assets/scripts/index.js\"></script>\r\n" + "  </body>\r\n" + "</html>\r\n" + "");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();

    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String email = request.getParameter("email");
    String country = request.getParameter("country");

    EmployeeEntity e = new EmployeeEntity();

    e.setName(name);
    e.setPassword(password);
    e.setEmail(email);
    e.setCountry(country);
    e.setId(ids);

    int status = EmployeeDAO.update(e);
    if (status > 0) {
      out.print("<body><script type=\"text/javascript\">\r\n" + "  	function redirect(){  		\r\n"
          + "  		alert(\"Record Update successfully!\");\r\n"
          + "  		window.location.replace(\"http://localhost:8080/TP-01/ReadServlet\");\r\n" + "  		\r\n"
          + "  	}\r\n" + "  	redirect()\r\n" + "  </script></body>");
    } else {
      out.print("<body><script type=\"text/javascript\">\r\n" + "  	function redirect(){  		\r\n"
          + "  		alert(\"Sorry! unable to update record!\");\r\n"
          + "  		window.location.replace(\"http://localhost:8080/TP-01/ReadServlet\");\r\n" + "  		\r\n"
          + "  	}\r\n" + "  	redirect()\r\n" + "  </script></body>");
    }
  }
}
