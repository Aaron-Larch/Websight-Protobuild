package JavaCallFile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		//String value=request.getParameter("choice");
 		//request.setAttribute("Record", Value);
		//request.getRequestDispatcher("/WEB-INF/DisplayPage.jsp").forward(request, response);
	}
}
