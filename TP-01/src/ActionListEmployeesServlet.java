import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.gson.Gson;

import DAO.EmployeeDAO;
import Entities.EmployeeEntity;
import Entities.ResponseResult;

public class ActionListEmployeesServlet extends HttpServlet {
  private Gson _jsonHandler;

  public ActionListEmployeesServlet() {
    super();
    this._jsonHandler = new Gson();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ResponseResult result;

    try {
      List<EmployeeEntity> employees = EmployeeDAO.getAllEmployees();

      result = new ResponseResult(true, "", employees);
    } catch (Exception exception) {
      result = new ResponseResult(false, exception.getMessage(), exception);
    }

    PrintWriter printWritter = response.getWriter();
    printWritter.write(this._jsonHandler.toJson(result));
    printWritter.flush();
  }
}
