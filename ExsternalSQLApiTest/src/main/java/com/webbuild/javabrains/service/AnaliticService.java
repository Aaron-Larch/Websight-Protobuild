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
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	static Map<String, Double> chartinfo= new HashMap<String, Double>();
	static double[][] databox=SpainShippingController.FetchValues();
	static String Name=SpainShippingController.FetchNameValues();
	private static Reports[][] box=new Reports[3][databox.length];
	private static int i=0;
	private static int ii=0;
	
	
	public static String BuildRecord(String[] choices, String Countery, int location){
		//Create a stream to hold the output
	    if(i < box.length && ii < databox.length) {
	    	box[i][ii] =  new Reports(); //create new report
	    	box[i][ii].setreportId(Name+"-"+Countery+"."+(ii+1)); //unique flag or primary key for report
	    	
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
	
	public static ModelAndView DisplayPage(ModelAndView model, Reports[] statement, int j){
	    double[] bellCurveGraph=new double[statement[j].getoriginal().length];
		int[] Xaxis =new int[statement[j].getoriginal().length+1];
		double[] BoxPlot=new double[5];
		chartinfo.clear();
		
		
		for(int i=0; i<Xaxis.length; i++) {Xaxis[i]=i;}
		Reports temp=new Reports();
		String[] populate = {"sorthi","sortlo","average","median","mode","max","min"};
		SwitchBoard.buildReports(temp, populate, statement[j].getoriginal());
		
		runSoftware.start();
		statement[j].showRecord();
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
		for(int jj=0; jj<temp.getlowC().length; jj++) {
			double part2= Math.pow((temp.getlowC()[jj]-temp.getaverage()),2);
			double part3= 2*chartinfo.get("Sample Variance");
			bellCurveGraph[jj]=part1*Math.exp(-1*part2 /part3); //*statement[i].getlowC().length; 
		}
		

		String[] barXaxis = new String[tempMap.size()];
		double[] barYaxis = new double[tempMap.size()];
		
		int jj=0;
		for (Map.Entry<String, Double> entry : tempMap.entrySet()) {
			barXaxis[jj] = entry.getKey();
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
	
	public static double[] getData(int array) {
		return databox[array];
	}
	
	public static void UpdateRecord() {
		i++;
		ii=0;
		Name=null;
	}
	
	public static void CloseRecord() {
		i=0;
		ii=0;
	}
	public static Reports[][] getFile(){
		return box;
	}
	
	public static void releaseresources() {
		i=0;
		ii=0;
		databox=null;
		Name=null;
		box=null;
	}
}
