package com.webbuild.javabrains.Operations;

import java.util.Arrays;

import com.webbuild.javabrains.Store;
import com.webbuild.javabrains.model.Reports;

public class BuildPath {
	public static Store scan=new Store();
	public static BuildPath Build = new BuildPath();
	
	//This is a Java interface to run through all of the properties for building an multi-dimensional
	// arrays for for testing purposes In the Java console.
	public  Reports[][] JavaInterFaceBuildArray(int[] length, int[] random){
		Reports[][] box =new Reports[3][]; //by leavening the second field empty it can be dynamically filled with later methods
		
		//the main build loop to generate arrays and create objects
		for(int i=0; i<box.length; i++) {
			System.out.println("Enter the name of Your Array:");//prompt the user to create the primary key value
			String name = scan.words(); // Scans the next token of the input as a String.
			
			//build a multi-dimensional set of arrays for the user to work with
			double[][] array =Build.arrayBoxInt(length,random, name); 
			System.out.println("do you want to analize " + name + ":");//Confirmation line
			String choice = scan.words(); // Scans the next token of the input as a String.
			if ("yes".equalsIgnoreCase(choice)|"y".equalsIgnoreCase(choice)) 
			{box[i]=SwitchBoard.JavaInterFaceBuildObject(array,name);} //create and populate an array of objects
			else if("no".equalsIgnoreCase(choice)|"n".equalsIgnoreCase(choice)) {i--;} //by decrementing i the for loop becomes infinite  
			else {break;}//cut off point to ease testing
		}
		return box;
	}
	
	//build a multi-dimensional set of arrays of integer values and then convert the values to a doubles
	public double[][] arrayBoxInt(int[] size, int[] range, String name){
		double[][] arrayBox = new double [size.length][];
		for(int i=0; i<arrayBox.length; i++) {//create a variable that represents each outter array
			int[] tempi= scan.rArrayInt(size[i], range[i]);//populate the inner array
			double[] tempd = new double [tempi.length]; //convert int values into doubles
			for(int j=0; j<tempi.length;j++) {
				tempd[j]=tempi[j];
			}
			arrayBox[i]=tempd;//add the inner array to the multi array
			//print out the results to confirm results 
			System.out.print("The sample size taken for this week was " + Math.abs(size[i]) + 
					". The highest recorded value for this stock was " + range[i] + "\n" +
					"The stock values for " + name + " in Region "+i+": " + Arrays.toString(arrayBox[i]) + "\n");
		}
		return arrayBox;
	}
	
	//build a multi-dimensional set of arrays of double values
	public double[][] arrayBoxDouble(int[] size, int[] range, String name){
		double[][] arrayBox = new double [size.length][];
		for(int i=0; i<arrayBox.length; i++) { //create a variable that represents each outter array
			arrayBox[i]=scan.rArrayDouble(size[i], range[i]); //populate the inner array
			//print out the results to confirm results 
			System.out.print("This is your array " + name + ". This array has " + Math.abs(size[i])
			+ " enteries and the largest number is " + range[i] + "\n" + name + ": " + Arrays.toString(arrayBox[i]) + "\n");
		}
		return arrayBox;
	}
	
	//build a array of integer values
	public static int[] buildArray(String name) {
		System.out.println("Enter the size of Your Array:");
		int size = scan.number(); // Scans the next token of the input as an int.
		System.out.println("Enter the upper range of Your Array:");
		int range = scan.number(); // Scans the next token of the input the range of random numbers.
		// once finished
		int[] array = scan.rArrayInt(size, range);
		System.out.print("This is your array " + name + ". This array has " + Math.abs(size)
				+ " enteries ant the largest number is " + range + "\n" + name + ": " + Arrays.toString(array) + "\n");
		return array;
	}
	
	//prototype java interface
	public Reports[] BuildRecord(int[] length, int[] random ){
		System.out.println("Enter the name of Your Array:");
		String name = scan.words(); // Scans the next token of the input as a String.
		double[][] array =Build.arrayBoxInt(length,random, name);//create a double array
		Reports[] box=SwitchBoard.JavaInterFaceBuildObject(array,name);//create and populate an array of objects
		return box;
	}
	
	//a stored method containing all secondary calculations method calls
	public static void complexOps(Reports[] temp) {
		//Execute secondary calculations on every saved search result object
		if (temp.length==0) {System.out.println("There are no records matching your query");}
		else {
			for(int j=0; j < temp.length; j++) {
				if(temp[j] != null) {
					System.out.println(temp[j].getreportId()+": "+Arrays.toString(temp[j].gethighC()));
					Statistics.SampleVariance(temp[j]);
					Statistics.Range(temp[j]);
				}
			}
			Statistics.FrequencyTable(temp);
		}
	}
}
