

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		 String name = request.getParameter("name");
		 String password = request.getParameter("password");
		 String email = request.getParameter("email");
		 String country = request.getParameter("country");
		
		 Employee e=new Employee();
		 e.setName(name);
		 e.setPassword(password);
		 e.setEmail(email);
		 e.setCountry(country);
		
		 int status = employeeDAO.save(e);
		 if(status>0){
			 out.print("<body><script type=\"text/javascript\">\r\n" + 
			 		"  	function redirect(){  		\r\n" + 
			 		"  		alert(\"Record saved successfully!\");\r\n" + 
			 		"  		window.location.replace(\"http://localhost:8080/TP-01/\");\r\n" + 
			 		"  		\r\n" + 
			 		"  	}\r\n" + 
			 		"  	redirect()\r\n" + 
			 		"  </script></body>");
			 
		 }
		 else{
		  out.print("<body> <script type=\"text/javascript\"> alert(\"Sorry! unable to save record!\")</script> </body>");
		 }
		 out.close();
		 }
		 

	}
