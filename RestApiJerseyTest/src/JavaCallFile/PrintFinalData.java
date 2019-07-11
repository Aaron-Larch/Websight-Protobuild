/**
 * 
 */
package JavaCallFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
 *http://localhost:8080/RestApiJerseyTest/PrintFinalData
 */
@WebServlet("/PrintFinalData")
public class PrintFinalData extends HttpServlet{
	Map<String, Double> chartinfo= new HashMap<String, Double>();
	public int i=0;
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
	    Reports[] statement = (Reports[]) request.getSession().getAttribute("Final");
		double[] bellCurveGraph=new double[statement[i].getlowC().length];
		int[] Xaxis =new int[statement[i].getlowC().length+1];
		double[] BoxPlot=new double[5];
		
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
		chartinfo.putAll(Statistics.HistogramTable(statement[i]));
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
		
		int[][] bin = Statistics.BuildBins(statement[i]);
		double[] barYaxis=new double[bin.length];
		for(int i=0; i<bin.length; i++) {
			barYaxis[i]=chartinfo.get(Arrays.toString(bin[i]));
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
		request.setAttribute("BarGraph", bin);

		

	}
}
