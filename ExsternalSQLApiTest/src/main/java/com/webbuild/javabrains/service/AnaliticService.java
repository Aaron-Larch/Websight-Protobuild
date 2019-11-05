package com.webbuild.javabrains.service;

import com.webbuild.javabrains.ConsoleOutputCapturer;
import com.webbuild.javabrains.Operations.SwitchBoard;
import com.webbuild.javabrains.controller.SpainShippingController;
import com.webbuild.javabrains.model.Reports;


public class AnaliticService{// implements AnaliticsRepository{
	private static ConsoleOutputCapturer runSoftware= new ConsoleOutputCapturer();
	static double[][] databox=SpainShippingController.FetchValues();
	public static Reports[][] box=new Reports[3][databox.length];
	private static int i=0;
	private static int ii=0;
	
	
	public static String BuildRecord(String[] choices, String Countery, int location){
		//Create a stream to hold the output
	    if(i < box.length) {
	    	box[i][ii] =  new Reports(); //create new report
	    	box[i][ii].setreportId("tom"+"-"+Countery+"."+(ii+1)); //unique flag or primary key for report
	    	
	    	runSoftware.start();
	    	System.out.println("The Report Created For Record "+ box[i][ii].getreportId()+" Is: ");
		 	SwitchBoard.buildReports(box[i][ii], choices, databox[location]);//populate the Report
		 	String printOutputValue=runSoftware.stop();
		 	ii++;
		 	return printOutputValue;
		 }
	    return null;
	}

	public static double[] getData(int array) {
		return databox[array];
	}
	
	
	
}
