/**
 * 
 */
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
import javaDemo.SwitchBoard;

/**
 * @author gce
 *http://localhost:8181/RestApiJerseyTest/
 */
@WebServlet("/PrintFinalData")
public class PrintFinalData extends HttpServlet{
	Map<String, Double> chartinfo= new HashMap<String, Double>();
	public int i=0;
	public static Reports[] statement;
	public static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
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
	    double[] bellCurveGraph=new double[statement[i].getoriginal().length];
		int[] Xaxis =new int[statement[i].getoriginal().length+1];
		double[] BoxPlot=new double[5];
		chartinfo.clear();
		
		for(int j=0; j<Xaxis.length; j++) {Xaxis[j]=j;}
		Reports temp=new Reports();
		String[] populate = {"sorthi","sortlo","average","median","mode","max","min"};
		SwitchBoard.buildReports(temp, populate, statement[i].getoriginal());
		
		runSoftware.start();
		statement[i].showRecord();
		chartinfo=Statistics.SampleVariance(temp);
		chartinfo.putAll(Statistics.Range(temp));
		Map<String, Double> tempMap=Statistics.HistogramTable(temp);
		String output=runSoftware.stop();
		
		BoxPlot[0]=temp.getmin();
		BoxPlot[1]=chartinfo.get("lower interquartile range");
		BoxPlot[2]=chartinfo.get("upper interquartile range");
		BoxPlot[3]=temp.getmax();
		BoxPlot[4]=temp.getmedian();
		
		double part1 = 1/(chartinfo.get("Standard Deveation") * Math.sqrt(2 * Math.PI));
		for(int j=0; j<temp.getlowC().length; j++) {
			double part2= Math.pow((temp.getlowC()[j]-temp.getaverage()),2);
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
		request.setAttribute("Message", output);
		request.setAttribute("Label", statement[i].getreportId());
		request.setAttribute("Xaxis", Xaxis);
		request.setAttribute("YaxisHigh", temp.gethighC());
		request.setAttribute("YaxisLow", temp.getlowC());
		request.setAttribute("BellCurveGraph", bellCurveGraph);
		request.setAttribute("BoxAndWhiskersGraph", BoxPlot);
		request.setAttribute("Histogram", barYaxis);
		request.setAttribute("BarGraph", barXaxis);
	}
}
