package JavaCallFile;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
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
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		Reports[] statement = (Reports[]) request.getSession().getAttribute("Final");
		Map<Double, Integer> chartinfo= new HashMap<Double, Integer>();
		int i=0;
		
		runSoftware.start();
		chartinfo=Statistics.FrequencyTable(statement);
		runSoftware.stop();
		
		double[] modes = new double[chartinfo.size()];
		int[] NumOfHit = new int[chartinfo.size()];
		for(Map.Entry<Double, Integer> entry : chartinfo.entrySet()) {
			modes[i]=entry.getKey();
			NumOfHit[i]=entry.getValue();
			i++;
		}
		
 		request.setAttribute("table", chartinfo);
		request.setAttribute("keys", Arrays.toString(modes));
		request.setAttribute("values", Arrays.toString(NumOfHit));
		request.setAttribute("colors", generateColors(modes));
		request.getRequestDispatcher("/WEB-INF/FinalWebPage.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		Reports[] statement = (Reports[]) request.getSession().getAttribute("Final");
		String pathflag= request.getParameter("action");
		String FilePath= request.getParameter("Filepath");
		
		if(pathflag.equalsIgnoreCase("Exit Program")) {
			request.getSession().removeAttribute("Final");
			request.getSession().removeAttribute("ClearAll");
			request.getRequestDispatcher("/IntroPage.jsp").forward(request, response);
		}else {
		    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FilePath, true)));
		    for(int i=0; i<statement.length; i++) {
			 	runSoftware.start();
			 	statement[i].showRecord();
				Statistics.SampleVariance(statement[i]);
				Statistics.Range(statement[i]);
			 	String printStatement=runSoftware.stop();
			 	System.out.println(printStatement);
				writer.write(printStatement);
				writer.write("\n");
		    }
		    writer.close();
		    doGet(request, response);
		}

	}
	
	// Generates random unique color set for each bin in the pie or doughnut chart.
	public static String generateColors(double[] frequencyTable) {
		double[] values = frequencyTable;
		String[] colors = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			colors[i] = String.format("\"#%06X\"", (int)(Math.random() * Math.pow(16, 6)));
		}
		return Arrays.toString(colors);
	}
}
