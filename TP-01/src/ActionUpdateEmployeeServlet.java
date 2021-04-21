import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import DAO.EmployeeDAO;
import Entities.EmployeeEntity;
import Entities.ResponseResult;

public class ActionUpdateEmployeeServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionUpdateEmployeeServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      String requestBody = IOUtils.toString(request.getReader());
      EmployeeEntity employee = this._jsonHandler.fromJson(requestBody, EmployeeEntity.class);

      EmployeeEntity employeeFound = EmployeeDAO.getEmployeeById(employee.getId());
      boolean employeeExists = employeeFound != null && employeeFound.getId() != 0;

      String message;
      boolean updatedSuccessfully = false;

      if (!employeeExists) {
        message = "Employee not found.";
      } else {
        updatedSuccessfully = EmployeeDAO.update(employee) > 0;

        if (updatedSuccessfully)
          message = "Employee updated successfully.";
        else
          message = "An error occurred while updating employee.";
      }

      boolean everythingIsOk = employeeExists && updatedSuccessfully;

      result = new ResponseResult(everythingIsOk, message, new Object());
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
