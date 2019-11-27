package com.webbuild.javabrains.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.springframework.web.servlet.ModelAndView;

import com.webbuild.javabrains.ConsoleOutputCapturer;
import com.webbuild.javabrains.Operations.Statistics;
import com.webbuild.javabrains.Operations.SwitchBoard;
import com.webbuild.javabrains.controller.SpainShippingController;
import com.webbuild.javabrains.model.Reports;



public class AnaliticService{// implements AnaliticsRepository{
	//Declare all Required Global Variables
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	static Map<String, Double> chartinfo= new HashMap<String, Double>();
	static double[][] databox=SpainShippingController.FetchValues();
	private static Reports[][] box=new Reports[3][];
	private static int i=-1; //Start at -1 to run UpdateRecord and initialize array 0 location
	private static int ii=0;
	
	//build a single object also the first method called in program
	public static String BuildRecord(String[] choices, String Countery, String Name, int location){
		//Start the Record creation process
		if(i==-1) {UpdateRecord();}
		
		//Create a stream to hold the output
	    if(i < box.length && ii < databox.length) {
	    	box[i][ii] =  new Reports(); //create new report
	    	box[i][ii].setreportId(Name+"-"+Countery+"."+(ii+1)); //unique flag or primary key for report
	    	/*For predictive models will probably make a storage object or arry and populate it here */
	    	runSoftware.start();
	    	System.out.println("The Report Created For Record "+ box[i][ii].getreportId()+" Is: ");
		 	SwitchBoard.buildReports(box[i][ii], choices, databox[location]);//populate the Report
		 	String printOutputValue=runSoftware.stop();
		 	ii++;
		 	return printOutputValue;
		 }else {
			 String error ="You have currently reached the limit of data that can be stored in this file.";
			 return error;
		 }
	}
	
	//Store a print out of every object currently stored
	public static String[] getObjInfo() {
		ArrayList<String> storage= new ArrayList<String>();
		for(int j=0; j<box.length; j++) {
			if(box[j]!=null) {
				for(int k=0; k<box[j].length; k++) {
					if(box[j][k]!=null) {
						runSoftware.start();
						box[j][k].showRecord();
						System.out.print("break");
					 	String printOutputValue=runSoftware.stop();
					 	storage.add(printOutputValue);
					}
				}
			}
		}
		String[] PopUp=storage.toArray(new String[storage.size()]);
		return PopUp;
	}
	
	//gather all the required variables need to print a report. Including histogram, bell curve, box and whisker, and predictive models 
	public static ModelAndView DisplayPage(ModelAndView model, Reports[] statement, int j){
	    double[] bellCurveGraph=new double[statement[j].getoriginal().length];
		int[] Xaxis =new int[statement[j].getoriginal().length+1];
		double[] BoxPlot=new double[5];
		chartinfo.clear();
		
		//Create a temp object to gather all of the required data to make the graphs 
		for(int i=0; i<Xaxis.length; i++) {Xaxis[i]=i;}
		Reports temp=new Reports();
		temp.setreportId(statement[j].getreportId());
		String[] populate = {"sorthi","sortlo","average","median","mode","max","min"};
		SwitchBoard.buildReports(temp, populate, statement[j].getoriginal());
		
		//run calculation for range and sample variance
		runSoftware.start();
		statement[j].showRecord();
		chartinfo=Statistics.SampleVariance(temp); // calculate standard deviation and sample variance 
		chartinfo.putAll(Statistics.Range(temp)); //calculate range and quadrants for box and whisker graphs
		Map<String, Double> tempMap=Statistics.HistogramTable(temp);
		String output=runSoftware.stop();
		
		//store range data into object to chart out box and whisker graph
		BoxPlot[0]=temp.getmin();
		BoxPlot[1]=chartinfo.get("lower interquartile range");
		BoxPlot[2]=chartinfo.get("upper interquartile range");
		BoxPlot[3]=temp.getmax();
		BoxPlot[4]=temp.getmedian();
		
		//formula to calculate a bell curve
		double part1 = 1/(chartinfo.get("Standard Deveation") * Math.sqrt(2 * Math.PI));
		for(int jj=0; jj<temp.getlowC().length; jj++) {
			double part2= Math.pow((temp.getlowC()[jj]-temp.getaverage()),2);
			double part3= 2*chartinfo.get("Sample Variance");
			bellCurveGraph[jj]=part1*Math.exp(-1*part2 /part3); //*statement[i].getlowC().length; 
		}
		
		//Store values for a histogram graph
		String[] barXaxis = new String[tempMap.size()];
		double[] barYaxis = new double[tempMap.size()];
		
		int jj=0;
		for (Map.Entry<String, Double> entry : tempMap.entrySet()) {
			 String[] format = entry.getKey().substring(1, entry.getKey().length()-1).split(", ");
			 
			 //if range is large then use this to change the information into an abbreviated representation of the data
			 if(format.length > 10) {
				 String lable ="["+ format[0]+", "+format[format.length/2] +", "+format[format.length-1]+"]";
				 barXaxis[jj]=lable;
			 }else {barXaxis[jj] = entry.getKey();}
			barYaxis[jj] = entry.getValue();
			jj++;
		}
		
		//print output
		model.addObject("Message", output);
		model.addObject("Label", statement[j].getreportId());
		model.addObject("Xaxis", Xaxis);
		model.addObject("YaxisHigh", temp.gethighC());
		model.addObject("YaxisLow", temp.getlowC());
		model.addObject("BellCurveGraph", bellCurveGraph);
		model.addObject("BoxAndWhiskersGraph", BoxPlot);
		model.addObject("Histogram", barYaxis);
		model.addObject("BarGraph", barXaxis);
		
		return model;
	}
	
	//Return Stored values to the controller for display
	public static double[] getData(int array) {return databox[array];}
	public static Reports[][] getFile(){return (box);}
	
	//Create a new row it the custom records table
	public static void UpdateRecord() {
		i++;
		ii=0;
		box[i]=new Reports[databox.length];
	}

	//erase all saved values to start collect new data
	public static void CloseRecord() {
		i=-1; //Start at -1 to run UpdateRecord and initialize array 0 location
		ii=0;
	}
	
	//Close a recored and relese the stored resources for data effishency
	public static void releaseresources() {
		CloseRecord();
		databox=null;
		box=new Reports[3][];
	}
}
