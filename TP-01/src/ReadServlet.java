

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadServlet
 */
@WebServlet("/ReadServlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		List<Employee> list = employeeDAO.getAllEmployees();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if(list.size() > 0) {
			out.println("<!DOCTYPE html>\r\n" + 
					"<html lang=\"pt-br\">\r\n" + 
					"  <head>\r\n" + 
					"    <meta charset=\"UTF-8\" />\r\n" + 
					"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
					"\r\n" + 
					"    <title>SWI | 5 - TP 01 - Employees</title>\r\n" + 
					"\r\n" + 
					"    <link rel=\"stylesheet\" href=\"employee.css\" />\r\n" + 
					"    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" />\r\n" + 
					"    <link\r\n" + 
					"      href=\"https://fonts.googleapis.com/css2?family=Raleway:wght@100;400;700&display=swap\"\r\n" + 
					"      rel=\"stylesheet\"\r\n" + 
					"    />\r\n" + 
					"  </head>\r\n" + 
					"  <body>\r\n" + 
					"    <nav class=\"navbar\">\r\n" + 
					"      <div>\r\n" + 
					"        <a href=\"http://localhost:8080/TP-01/\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"none\"\r\n" + 
					"            stroke=\"currentColor\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            stroke-linecap=\"round\"\r\n" + 
					"            stroke-linejoin=\"round\"\r\n" + 
					"            class=\"feather feather-arrow-left\"\r\n" + 
					"          >\r\n" + 
					"            <line x1=\"19\" y1=\"12\" x2=\"5\" y2=\"12\"></line>\r\n" + 
					"            <polyline points=\"12 19 5 12 12 5\"></polyline>\r\n" + 
					"          </svg>\r\n" + 
					"        </a>\r\n" + 
					"      </div>\r\n" + 
					"    </nav>\r\n" + 
					"");
			out.println(" <main class=\"container\">\r\n" + 
					"      <h1>Employees</h1>\r\n" + 
					"\r\n" + 
					"      <table class=\"employee-list\">\r\n" + 
					"        <thead>\r\n" + 
					"          <tr>\r\n" + 
					"            <th>#</th>\r\n" + 
					"            <th>Name</th>\r\n" + 
					"            <th>Password</th>\r\n" + 
					"            <th>E-mail</th>\r\n" + 
					"            <th>Country</th>\r\n" + 
					"            <th>Actions</th>\r\n" + 
					"          </tr>\r\n" + 
					"        </thead>\r\n" + 
					"        <tbody>\r\n" + 
					
					"");
			for(Employee emp: list) {
				out.print("          <tr>\r\n" + 
						"          \r\n" + 
						"            <td>"+emp.getId()+"</td>\r\n" + 
						"            <td>"+emp.getName()+"</td>\r\n" + 
						"            <td>"+emp.getPassword()+"</td>\r\n" + 
						"            <td>"+emp.getEmail()+"</td>\r\n" + 
						"            <td>"+emp.getCountry()+"</td>\r\n" + 
						"            <td class=\"employee-actions\">\r\n" + 
						"              <button onclick=\"handleEdit("+ emp.getId() +")\">\r\n" + 
						"                <svg\r\n" + 
						"                  xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
						"                  width=\"24\"\r\n" + 
						"                  height=\"24\"\r\n" + 
						"                  viewBox=\"0 0 24 24\"\r\n" + 
						"                  fill=\"none\"\r\n" + 
						"                  stroke=\"currentColor\"\r\n" + 
						"                  stroke-width=\"2\"\r\n" + 
						"                  stroke-linecap=\"round\"\r\n" + 
						"                  stroke-linejoin=\"round\"\r\n" + 
						"                  class=\"feather feather-edit\"\r\n" + 
						"                >\r\n" + 
						"                  <path\r\n" + 
						"                    d=\"M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7\"\r\n" + 
						"                  ></path>\r\n" + 
						"                  <path\r\n" + 
						"                    d=\"M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z\"\r\n" + 
						"                  ></path>\r\n" + 
						"                </svg>\r\n" + 
						"              </button>\r\n" + 
						"\r\n" + 
						"              <button onclick=\"handleDelete("+ emp.getId()+")\">\r\n" + 
						"                <svg\r\n" + 
						"                  xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
						"                  width=\"24\"\r\n" + 
						"                  height=\"24\"\r\n" + 
						"                  viewBox=\"0 0 24 24\"\r\n" + 
						"                  fill=\"none\"\r\n" + 
						"                  stroke=\"currentColor\"\r\n" + 
						"                  stroke-width=\"2\"\r\n" + 
						"                  stroke-linecap=\"round\"\r\n" + 
						"                  stroke-linejoin=\"round\"\r\n" + 
						"                  class=\"feather feather-trash-2\"\r\n" + 
						"                >\r\n" + 
						"                  <polyline points=\"3 6 5 6 21 6\"></polyline>\r\n" + 
						"            	      <path\r\n" + 
						"                    d=\"M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2\"\r\n" + 
						"                  ></path>\r\n" + 
						"                  <line x1=\"10\" y1=\"11\" x2=\"10\" y2=\"17\"></line>\r\n" + 
						"                  <line x1=\"14\" y1=\"11\" x2=\"14\" y2=\"17\"></line>\r\n" + 
						"                </svg>\r\n" + 
						"              </button>\r\n" + 
						"            </td>\r\n" + 
						"          </tr>\r\n" + 
						"          ");
					
	
		}
			out.println("        </tbody>\r\n" + 
					"      </table>\r\n" + 
					"    </main>\r\n" + 
					"    <nav class=\"navbar\">\r\n" + 
					"      <a\r\n" + 
					"        title=\"Show me the code 🙃\"\r\n" + 
					"        href=\"https://github.com/angeloevangelista/SW1-5\"\r\n" + 
					"        target=\"_blank\"\r\n" + 
					"        rel=\"noopener noreferrer\"\r\n" + 
					"      >\r\n" + 
					"        <div class=\"github-logo\"></div>\r\n" + 
					"      </a>\r\n" + 
					"\r\n" + 
					"      <div class=\"navbar-bottom-group\">\r\n" + 
					"        <a href=\"http://localhost:8080/TP-01/authors.html\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"none\"\r\n" + 
					"            stroke=\"currentColor\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            stroke-linecap=\"round\"\r\n" + 
					"            stroke-linejoin=\"round\"\r\n" + 
					"            class=\"feather feather-star\"\r\n" + 
					"          >\r\n" + 
					"            <polygon\r\n" + 
					"              points=\"12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2\"\r\n" + 
					"            ></polygon>\r\n" + 
					"          </svg>\r\n" + 
					"        </a>\r\n" + 
					"      </div>\r\n" + 
					"    </nav>\r\n" + 
					"\r\n" + 
					"    <div class=\"edit-modal\" name=\"edit-modal\">\r\n" + 
					"      <form class=\"form\" action=\"#\">\r\n" + 
					"        <div class=\"input-container\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"none\"\r\n" + 
					"            stroke=\"currentColor\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            stroke-linecap=\"round\"\r\n" + 
					"            stroke-linejoin=\"round\"\r\n" + 
					"            class=\"feather feather-user\"\r\n" + 
					"          >\r\n" + 
					"            <path d=\"M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2\"></path>\r\n" + 
					"            <circle cx=\"12\" cy=\"7\" r=\"4\"></circle>\r\n" + 
					"          </svg>\r\n" + 
					"\r\n" + 
					"          <input type=\"text\" name=\"name\" placeholder=\"Name\" />\r\n" + 
					"        </div>\r\n" + 
					"\r\n" + 
					"        <div class=\"input-container\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"transparent\"\r\n" + 
					"            stroke=\"%23dae8eb\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            class=\"feather feather-at-sign\"\r\n" + 
					"          >\r\n" + 
					"            <circle cx=\"12\" cy=\"12\" r=\"4\"></circle>\r\n" + 
					"            <path d=\"M16 8v5a3 3 0 0 0 6 0v-1a10 10 0 1 0-3.92 7.94\"></path>\r\n" + 
					"          </svg>\r\n" + 
					"\r\n" + 
					"          <input type=\"email\" name=\"email\" placeholder=\"E-mail\" />\r\n" + 
					"        </div>\r\n" + 
					"\r\n" + 
					"        <div class=\"input-container\" data-error=\"false\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"none\"\r\n" + 
					"            stroke=\"currentColor\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            stroke-linecap=\"round\"\r\n" + 
					"            stroke-linejoin=\"round\"\r\n" + 
					"            class=\"feather feather-lock\"\r\n" + 
					"          >\r\n" + 
					"            <rect x=\"3\" y=\"11\" width=\"18\" height=\"11\" rx=\"2\" ry=\"2\"></rect>\r\n" + 
					"            <path d=\"M7 11V7a5 5 0 0 1 10 0v4\"></path>\r\n" + 
					"          </svg>\r\n" + 
					"\r\n" + 
					"          <input type=\"password\" name=\"password\" placeholder=\"Password\" />\r\n" + 
					"        </div>\r\n" + 
					"\r\n" + 
					"        <div class=\"input-container\" data-error=\"false\">\r\n" + 
					"          <svg\r\n" + 
					"            xmlns=\"http://www.w3.org/2000/svg\"\r\n" + 
					"            width=\"24\"\r\n" + 
					"            height=\"24\"\r\n" + 
					"            viewBox=\"0 0 24 24\"\r\n" + 
					"            fill=\"none\"\r\n" + 
					"            stroke=\"currentColor\"\r\n" + 
					"            stroke-width=\"2\"\r\n" + 
					"            stroke-linecap=\"round\"\r\n" + 
					"            stroke-linejoin=\"round\"\r\n" + 
					"            class=\"feather feather-map-pin\"\r\n" + 
					"          >\r\n" + 
					"            <path d=\"M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z\"></path>\r\n" + 
					"            <circle cx=\"12\" cy=\"10\" r=\"3\"></circle>\r\n" + 
					"          </svg>\r\n" + 
					"\r\n" + 
					"          <select name=\"country\"></select>\r\n" + 
					"        </div>\r\n" + 
					"		\r\n" + 
					"        <div class=\"form-footer\">\r\n" + 
					"          <button name=\"cancel-edit-button\" type=\"button\" class=\"secondary\">Cancel</button>\r\n" + 
					"          <button type=\"submit\" id=\"upx\" class=\"primary\">Update</button>\r\n" + 
					"        </div>\r\n" + 
					"      </form>\r\n" + 
					"    </div>" + 
					"    <script src=\"./assets/scripts/global.js\"></script>\r\n" + 
					"    <script src=\"./assets/scripts/employees.js\"></script>\r\n" + 
					
					"<script type=\"text/javascript\">\r\n" + 
					"	    function handleDelete(dados){\r\n" + 
					"	    	var url = \"http://localhost:8080/TP-01/DeleteServlet?id=\" + dados;\r\n" + 
					"	    	window.location.replace(url)	" + 
					"	    }\r\n" + 
					"	    function handleEdit(dados){\r\n" + 
					"	    	var url = \"http://localhost:8080/TP-01/UpdateServlet?id=\" + dados;  	\r\n" + 
					"window.location.replace(url)"+
					"	    }\r\n" + 
					"	    \r\n" + 
					"	</script>" +
					
					"   \r\n" + 
					"  </body>\r\n" + 
					"</html>");
		
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
