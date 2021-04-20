

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DeleteServlet() {
        super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Integer id = Integer.parseInt(request.getParameter("id"));
    	
    	Employee em = employeeDAO.getEmployeeById(id);
    	if(em != null) {
    		employeeDAO.delete(em.getId());
    		PrintWriter out = response.getWriter();
    		 out.print("<body><script type=\"text/javascript\">\r\n" + 
 			 		"  	function redirect(){  		\r\n" + 
 			 		"  		alert(\"employee successfully removed!\");\r\n" + 
 			 		"  		window.location.replace(\"http://localhost:8080/TP-01/ReadServlet\");\r\n" + 
 			 		"  		\r\n" + 
 			 		"  	}\r\n" + 
 			 		"  	redirect()\r\n" + 
 			 		"  </script></body>");
    	}
		response.getWriter().append("Served at: " + id + " aa ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at:  POST ").append(request.getContextPath());
	}

}
