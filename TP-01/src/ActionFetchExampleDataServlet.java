import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Entities.EmployeeEntity;

public class ActionFetchExampleDataServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    EmployeeEntity employee = new EmployeeEntity(1, "Example", "exam.ple@example.com", "123456", "Brazil");

    String employeeJsonString = new Gson().toJson(employee);

    PrintWriter printWritter = response.getWriter();

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    printWritter.write(employeeJsonString);
    printWritter.flush();
  }
}
