import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

import DAO.EmployeeDAO;
import Entities.EmployeeEntity;
import Entities.ResponseResult;

public class ActionDeleteEmployeeServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionDeleteEmployeeServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String message;

      Integer employeeId = Integer.parseInt(request.getParameter("id"));

      EmployeeEntity employee = EmployeeDAO.getEmployeeById(employeeId);
      boolean employeeExists = employee != null && employee.getId() != 0;

      if (!employeeExists) {
        message = "Employee not found.";
      } else {
        EmployeeDAO.delete(employee.getId());

        message = "Employee successfully deleted.";
      }

      result = new ResponseResult(employeeExists, message, null);
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
