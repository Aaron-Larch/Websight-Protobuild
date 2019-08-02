/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
 *http://localhost:8080/RestApiJerseyTest/
 */
@WebServlet("/PrintFinalData")
public class PrintFinalData extends HttpServlet{
	Map<String, Double> chartinfo= new HashMap<String, Double>();
	public int i=0;
	public static Reports[] statement;
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
		DisplayPage(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
		int value =Integer.parseInt(request.getParameter("action"));
		if(i>=0 && i<statement.length) {
			i += value;
			if(i==-1) {i=statement.length-1;}//make sure the back value can never go below 0
			else if(i==statement.length) {i=0;}//make sure the next value can never go above max stored value
		}
		DisplayPage(request, response);
		request.getRequestDispatcher("/WEB-INF/ChartBuild.jsp").forward(request, response);
	}
	
	private void DisplayPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    statement = (Reports[]) request.getSession().getAttribute("Final");
	    double[] bellCurveGraph=new double[statement[i].getlowC().length];
		int[] Xaxis =new int[statement[i].getlowC().length+1];
		double[] BoxPlot=new double[5];
		chartinfo.clear();
		
		for(int j=0; j<Xaxis.length; j++) {Xaxis[j]=j;}
		
		//Create a stream to hold the output
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		statement[i].showRecord();
		chartinfo=Statistics.SampleVariance(statement[i]);
		chartinfo.putAll(Statistics.Range(statement[i]));
		Map<String, Double> tempMap=Statistics.HistogramTable(statement[i]);
		// Put things back
		System.out.flush();
		System.setOut(old);
		
		BoxPlot[0]=statement[i].getmin();
		BoxPlot[1]=chartinfo.get("lower interquartile range");
		BoxPlot[2]=chartinfo.get("upper interquartile range");
		BoxPlot[3]=statement[i].getmax();
		BoxPlot[4]=statement[i].getmedian();
		
		double part1 = 1/(chartinfo.get("Standard Deveation") * Math.sqrt(2 * Math.PI));
		for(int j=0; j<statement[i].getlowC().length; j++) {
			double part2= Math.pow((statement[i].getlowC()[j]-statement[i].getaverage()),2);
			double part3= 2*chartinfo.get("Sample Variance");
			bellCurveGraph[j]=part1*Math.exp(-1*part2 /part3); //*statement[i].getlowC().length; 
		}
		

		String[] barXaxis = new String[tempMap.size()];
		double[] barYaxis = new double[tempMap.size()];
		
		int j=0;
		for (Map.Entry<String, Double> entry : tempMap.entrySet()) {
			barXaxis[j] = entry.getKey();
			barYaxis[j] = entry.getValue();
			j++;
		}
		
		//print output
		request.setAttribute("Message", baos.toString());
		request.setAttribute("Label", statement[i].getreportId());
		request.setAttribute("Xaxis", Xaxis);
		request.setAttribute("YaxisHigh", statement[i].gethighC());
		request.setAttribute("YaxisLow", statement[i].getlowC());
		request.setAttribute("BellCurveGraph", bellCurveGraph);
		request.setAttribute("BoxAndWhiskersGraph", BoxPlot);
		request.setAttribute("Histogram", barYaxis);
		request.setAttribute("BarGraph", barXaxis);
	}
}
