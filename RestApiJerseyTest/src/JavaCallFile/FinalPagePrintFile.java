package JavaCallFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaDemo.Reports;
import javaDemo.Statistics;

/**
 * @author gce
 * http://localhost:8181/RestApiJerseyTest/
 *
 */

@WebServlet("/FinalPagePrintFile")
public class FinalPagePrintFile extends HttpServlet{
	ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	String printOutputValue;
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		Reports[] statement = (Reports[]) request.getSession().getAttribute("Final");
		Map<Double, Integer> chartinfo= new HashMap<Double, Integer>();
		
		runSoftware.start();
		chartinfo=Statistics.FrequencyTable(statement);
		printOutputValue=runSoftware.stop();
		
 		request.setAttribute("table", chartinfo);
 		request.setAttribute("message", printOutputValue);
		request.getRequestDispatcher("/WEB-INF/FinalWebPage.jsp").forward(request, response);
	}
}
