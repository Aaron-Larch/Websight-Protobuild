/**
 * 
 */
package javaDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author gce
 *
 */
public class Statistics {
	/**
	 * 
	 */
	private static double temp;
	private static double value;
	private static double count=1;
	
	
	public static double[] sortEX(double[] array, String order) {
		if(order.contentEquals("hi")||order.contentEquals("lo")) { //error catching for the choice value
		sort(array, order); //delineate which value returned by user choice
		return array;
		}
		else {
			System.out.println("No reconized Input enered for the given function");
			return array;
			}
	}
	
	public static double average(double...array) {
		value = 0;
		//Average/Mean: (X1+X2+…+Xn)/n
		for (double i:array) {value = value + i;}
		value = value / array.length;
		return value;
	}

	public static Double minMax(double[] array, String choice) {
		sort(array, "hi");
		if (choice.equalsIgnoreCase("max")) { //delineate which value returned by user choice
			temp = array[0];
			return temp;
		} else if(choice.equalsIgnoreCase("min"))  { //delineate which value returned by user choice
			temp = array[array.length-1];
			return temp;
		}else { //error catching for the choice value
			System.out.println("No reconized Input enered for the given function");
			return null;
		}
	}

	public static double median(double...array) {
		double n1, n2;
		//Median: y=n/2 or (X(y-1)+X(y))/2
		int HalfWay = array.length / 2; //set y
		sort(array, "lo");
        if (array.length % 2 == 0){// if the remainder is 0 n is an even number
            n1 = array[HalfWay - 1];
            n2 = array[HalfWay];
            value = (n1 + n2)/2;
            return value;
        }else{
            value = array[HalfWay];
            return value;
        }
	}

	public static List<Double> mode(double...array) {
		List<Double> mode = new ArrayList<Double>();
		sort(array, "lo");
		double Count = 0;
		// run a basic sort method of coding
		for (double i:array) {
			temp = 0;
			for (double j:array) {
				if (j == i) {
					temp++;
				}
			}
			//Check to see if the mode value collected can be added to the list
			if (temp > Count && temp > 1) { //this loop always counts itself so temp will always be at least 1 so add && temp > 1 to fix
				Count = temp; // mode is the most accruing number this logic makes the number of reacuring numbers as high as possible
				mode.clear(); //prevents multiple mode sizes from being collected by reseting the list
				mode.add(i);
			} else if (temp == Count) {//a list could have multiple modes
				mode.add(i);
			}
		}
		if(mode.isEmpty()) {
			System.out.println("the given array has no mode");
			return Collections.emptyList();
		}
		else {return mode;}

	}
	
	public static Map<String, Double> SampleVariance(Reports file) {
		//Sample variance=(sum of range(Xi-mean)^2)/(n-1)
		Map<String, Double> export= new HashMap<String, Double>();
		double sum=0;
		for(int i=0; i<file.getlowC().length; i++) {
			sum+=Math.pow((file.getlowC()[i]-file.getaverage()),2);
			}
		value=sum/(file.getlowC().length-1);
		System.out.println("the Sample Variance of "+file.getreportId()+" is: "+value);
		export.put("Sample Variance", value); //store value
		
		//Standard Deviation=Math.sqrt(sample variance)
		System.out.println("the Standard Deviation of "+file.getreportId()+" is: "+Math.sqrt(value));
		export.put("Standard Deveation", Math.sqrt(value)); //store value
		return export;
	}
	
	public static Map<String, Double> Range(Reports file) {
		// TODO Auto-generated constructor stub
		Map<String, Double> export= new HashMap<String, Double>();
		int j=0;
		double range, LIQR, UIQR;
		double[] Q1=new double [(file.getlowC().length/2)];
		double[] Q3=new double [(file.getlowC().length/2)];
		range = file.getmax()-file.getmin();
		if(file.getlowC().length%2==0) {
			for(int i=0; i<file.getlowC().length; i++) {
				if(i < Q1.length) {Q1[i]=file.getlowC()[i];}
				else {
					Q3[j]=file.getlowC()[i];
					if(j<Q3.length-1) {j++;}
				}
			}
		}else {
			for(int i=0; i<file.getlowC().length; i++) {
				if(file.getlowC()[i] == file.getmedian()) {i++;}
				else if(i< Q1.length) {Q1[i]=file.getlowC()[i];}
				else {
					Q3[j]=file.getlowC()[i];
					if(j<(Q3.length-1)) {j++;}
				}
			}
		}
		LIQR=median(Q1);// lower interquartile range
		UIQR=median(Q3);// upper interquartile range
		System.out.println("lower interquartile range: "+LIQR);
		export.put("lower interquartile range", LIQR);
		System.out.println("upper interquartile range: "+UIQR);
		export.put("upper interquartile range", UIQR);
		System.out.println("interquartile range: "+(UIQR-LIQR));
		export.put("interquartile range", (UIQR-LIQR));
		//all that is needed for a whisker box chart
		System.out.println("range: "+range);
		return export;
	}
	
	public static Map<Double, Integer> FrequencyTable(Reports[] file) {
		Map<Double, Integer> map= new HashMap<Double, Integer>();
		for(int i=0; i < file.length; i++) {
			if(file[i]!=null && file[i].getmode()!=null) {
				for(int j=0; j < file[i].getmode().size(); j++){
					if(map.containsKey(file[i].getmode().get(j))){
						map.put(file[i].getmode().get(j), map.get(file[i].getmode().get(j)) + 1);
					} else {map.put(file[i].getmode().get(j), 1);}
			}
			System.out.println(map);
			}
		}
		return map;
	}
	
	/*int[][] bin = BuildBins(file);
	
	//build a frequency table with the bins created
	for(int i=0; i < file.getlowC().length; i++){
		for(int j=0; j<bin.length; j++) {
			//check to see if the array value is between the bin values.
			if(file.getlowC()[i] >= bin[j][0] && file.getlowC()[i]<=bin[j][bin[j].length-1]) {
				String bins= Arrays.toString(bin[j]);
				if(map.containsKey(bins)){map.put(bins, map.get(bins) + count);} 
				else {map.put(bins, count);}
			}
		}
	}
	//add zero values
	for(int i=0; i<bin.length; i++) {
		String bins= Arrays.toString(bin[i]);
		if(map.containsKey(bins)==false){map.put(bins, count--);}
	}
	System.out.println(map.size() + " map size");*/
	
	
	
	public static Map<String, Double> HistogramTable(Reports file) {	
		Map<Integer, Integer> sourceMap = buildHisto(file,5);
		Map<String, Double> displayMap= new LinkedHashMap<String, Double>();
		
		/*
		 * I used the LinkedHashMap, to
		 * transfer the values in the order of the pre-sorted original set
		 * see also https://stackoverflow.com/questions/663374/java-ordered-map
		 */
		
		for (Integer i: sourceMap.keySet()) {
			displayMap.put(i.toString(), sourceMap.get(i).doubleValue());
		}
		
		return displayMap;
	}
	
	/**
	 * 
	 * @param file generated report file
	 * @param width	range of the numbers i.e. the bin size
	 * @return	incremental count of numbers within bins, ascending
	 */
	
	public static Map<Integer, Integer> buildHisto(Reports file, int width) { //pass in length of bins and file
		Map<Integer, Integer> histo = new TreeMap<Integer,Integer>(); //set up array to count
		double[] data = file.getlowC();	// extract the data
		double topValue = data[data.length-1];
		
		for (int i = 0; i <= topValue; i+=width) { //lay out boundaries for each bin, only lower bound is needed
			histo.put(i,0);
		}
		
		for (double d : data) {	//increment up the appropriate bin for each value
			int bin = (int) (d / width) * width;
			int countForCurrentBin; 
			
			countForCurrentBin = histo.get(bin);
			
			histo.put(bin,++countForCurrentBin);
		}
		return histo;
	}
	
	public static int[][] BuildBins(Reports file) 
    {
		
		System.out.println("building bins for " + file.getreportId());
    	int[][] bin = null;
		int k=0;
		//Find the smallest size of bins that create less than 15 bins
		for(int i=5; i<(file.getmax()/5); i++) {//this is here to save memory. so where not going through the loop 50 times.
			int count=(int) ((file.getmax()+i)/i);
			//System.out.print(i + ": (");
			if (count<15) {
				//System.out.println("there will be "+(count+1)+" bins each bin has "+i+" values");
				bin =new int[count+1][i];
				for(int j=0; j<(file.getmax()+i); j+=i) {
					for(int h=0; h<bin[k].length; h++) {bin[k][h]=j+h;}//build the bin
					k++;
				}
				//System.out.println(")");
				break;//this is important. without this the loop starts again
			}
		}
		//System.out.println(Arrays.deepToString(bin));
		return bin;
    }
	
	private static void sort(double[] array, String order) {
		if (order.contentEquals("hi")) {
			// sort highest value to lowest value
			for (int i = 0; i < array.length; i++) { //create a variable for each value of the array
				for (int j = i + 1; j < array.length; j++) { //create a comparison variable for each other value of the array
					if (array[i] < array[j]) { //comparison statement changes with each type of sort
						temp = array[i]; //Store i value in a temp variable
						array[i] = array[j]; //posesion B = posesion A and posesion B = posesion B
						array[j] = temp; //posesion B = temp variable to complete the swap
					}
				}
			}
		} else if (order.contentEquals("lo")) {
			// sort lowest value to highest value
			for (int i = 0; i < array.length; i++) { //create a variable for each value of the array
				for (int j = i + 1; j < array.length; j++) { //create a comparison variable for each other value of the array
					if (array[i] > array[j]) { //comparison statement changes with each type of sort
						temp = array[i];  //Store i value in a temp variable
						array[i] = array[j];  //posesion B = posesion A and posesion B = posesion B
						array[j] = temp; //posesion B = temp variable to complete the swap
					}
				}
			}
		}	
	}
	
	private static void sortbykey(Map<String, Double> map) 
	{ 
		// TreeMap to store values of HashMap 
		TreeMap<String, Double> sorted = new TreeMap<>(); 

		// Copy all data from hashMap into TreeMap 
		sorted.putAll(map); 

		// Display the TreeMap which is naturally sorted 
		for (Map.Entry<String, Double> entry : sorted.entrySet())  
			System.out.println(entry.getKey() + ", Value = " + entry.getValue());         
	}
}