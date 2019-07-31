/**
 * 
 */
package javaDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
			} else if (temp == Count && mode.contains(i)==false) {//a list could have multiple modes
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
	
	public static Map<String, Double> HistogramTable(Reports file) {
		Map<List<Integer>, Integer> sourceMap = new TreeMap<>();
		Map<String, Double> displayMap= new LinkedHashMap<String, Double>();
		
		//Find the smallest size of bins that create less than 15 bins 5 seems like a good place to start
		for(int i=5; i<(file.getmax()/5); i++) {//this is here to save memory. so where not going through the loop 50 times.
			int count=(int) ((file.getmax())/i);
			if (count<15) {
				System.out.println("there will be "+(count+1)+" bins each bin has "+i+" values");
				sourceMap=buildHisto(file, i);
				break;//this is important. without this the loop starts again
			}
		}
		
		// Copy all data from hashMap into the LinkedHashMap and sort the map into numerical order, and a print statemnt
		displayMap=SortStingsNumericly(sourceMap);
		
		return displayMap;
	}
	
	private static Map<List<Integer>, Integer> buildHisto(Reports file, int width) { //pass in length of bins and file
		Map<List<Integer>, Integer> histo = new HashMap<List<Integer>,Integer>(); //set up array to count
		double[] data = file.getlowC();	// extract the data
		List<Integer> BinName= new ArrayList<Integer>();
		
		for (int i = 0; i <= file.getmax(); i+=width) { //lay out boundaries for each bin, only lower bound is needed
			List<Integer> tempBin= new ArrayList<Integer>();
			for(int j=i; j < (i+width); j++) {BinName.add(j);}
			tempBin.addAll(BinName);
			histo.put(tempBin,0);
			BinName.clear();
		}
		
		for (double d : data) {	//increment up the appropriate bin for each value
			int value=(int) d; // Handles decimal point values by dropping all values past the "."
			for(Map.Entry<List<Integer>, Integer> entry : histo.entrySet()) {//sort through all bins to find appropriate range
				List<Integer> key = entry.getKey();
				if(key.contains(value)) { //Check if value is in this bins range for each value
					int countForCurrentBin = histo.get(key);
					histo.put(key,++countForCurrentBin); //Increment by one
					break;//save memory when you find a match stop
				}
			}
		}
		return histo;
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
	
	private static Map<String, Double> SortStingsNumericly(Map<List<Integer>, Integer> map) {
		 Map<String, Double> sortedEntries = new LinkedHashMap<String, Double>();
		 double[] StoredSort = new double[map.size()];
		 int i=0;
		 for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
			 StoredSort[i]=entry.getKey().get(0);
			 i++;
		}
		sort(StoredSort, "lo"); 
		for (int j=0; j<StoredSort.length; j++) {
			 for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
				 if(entry.getKey().get(0)==StoredSort[j]) {
					 sortedEntries.put(entry.getKey().toString(), map.get(entry.getKey()).doubleValue());
				 }
			 }
		}
		
		// Display the TreeMap which is naturally sorted 
		sortedEntries.entrySet()
		.stream()
		.forEach(System.out::println);
		
		return sortedEntries;
	}
}