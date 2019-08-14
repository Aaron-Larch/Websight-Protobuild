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
	
	/*This is a buffer to access the private method "sort." "Sort" is a private method because almost every Method uses it
	 * but nothing outside of this class will use the "sort" method.*/
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
	
	/*This class is a library of methods focused on performing statistical equations, sorts, and other various operations.
	 * Each Method focuses on one equation or operation to allow for better control of the end product.  The Operations that
	 * can be performed here are:
	 * Average/Mean
	 * Min and Max
	 * median
	 * mode
	 * Sample Variance
	 * Standard Deviation
	 * Lower Interquartile range
	 * Upper Interquartile range
	 * Iinterquartile range
	 * Range
	 * Histogram Table
	 * Frequency Table
	 */
	
	public static double average(double...array) {
		value = 0;
		//calculation formula for Average/Mean: (X1+X2+…+Xn)/n
		for (double i:array) {value = value + i;}
		value = value / array.length;
		return value;
	}

	public static Double minMax(double[] array, String choice) {
		//the min and max values are always the first and last values of an array
		if (choice.equalsIgnoreCase("max")) { //delineate which value returned by user choice
			sort(array, "hi");
			temp = array[0];
			return temp;
		} else if(choice.equalsIgnoreCase("min"))  { //delineate which value returned by user choice
			sort(array, "lo");
			temp = array[0];
			return temp;
		}else { //error catching for the choice value
			System.out.println("No reconized Input enered for the given function");
			return null;
		}
	}

	public static double median(double...array) {
		double n1, n2;
		//Median: y=n/2 for lists of an odd length or (X(y-1)+X(y))/2 for lists of an even length
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
			} else if (temp == Count) {//a list could have multiple modes && mode.contains(i)==false for a single mode build
				mode.add(i);
			}
		}
		if(mode.isEmpty()) {//error handling in case there is no mode
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
	
	/*Given that there is little use for a Interquartile range outside of a box and whisker plot all
	 *range operations are grouped into a single method for more optimal organization */
	public static Map<String, Double> Range(Reports file) {
		// TODO Auto-generated constructor stub
		Map<String, Double> export= new HashMap<String, Double>();
		int j=0;
		double range, LIQR, UIQR;
		double[] Q1=new double [(file.getlowC().length/2)];
		double[] Q3=new double [(file.getlowC().length/2)];
		range = file.getmax()-file.getmin();
		if(file.getlowC().length%2==0) { //Check to see it the Quarter ranges will be even or odd
			//if even populate Array up to the median value
			for(int i=0; i<file.getlowC().length; i++) {
				if(i < Q1.length) {Q1[i]=file.getlowC()[i];}//use if statement to determine when to switch from Q2 to Q3 array
				else {
					Q3[j]=file.getlowC()[i];
					if(j<Q3.length-1) {j++;}
				}
			}
		}else {
			//if odd populate Array up to the median value then skip the median value 
			for(int i=0; i<file.getlowC().length; i++) {
				if(file.getlowC()[i] == file.getmedian()) {i++;}
				else if(i< Q1.length) {Q1[i]=file.getlowC()[i];}//use if statement to determine when to switch from Q2 to Q3 array
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
		export.put("range", range);
		return export;
	}
	
	/*This method looks at information across a large array of stored objects and builds a Frequency table.
	 *It should work with any parameter but for testing purposes I chose mode.*/
	public static Map<Double, Integer> FrequencyTable(Reports[] file) {
		Map<Double, Integer> map= new HashMap<Double, Integer>();
		for(int i=0; i < file.length; i++) {
			if(file[i]!=null && file[i].getmode()!=null) {//check to see if the object has the filed required for the sort
				for(int j=0; j < file[i].getmode().size(); j++){
					if(map.containsKey(file[i].getmode().get(j))){//check to see if the mode is already present in the table
						map.put(file[i].getmode().get(j), map.get(file[i].getmode().get(j)) + 1); //Increment count by 1
					} else {map.put(file[i].getmode().get(j), 1);} // add new record
			}
			System.out.println(map); //check statement
			}
		}
		return map;
	}
	
	public static Map<String, Double> HistogramTable(Reports file) {
		Map<List<Integer>, Integer> sourceMap = new TreeMap<>();
		Map<String, Double> displayMap= new LinkedHashMap<String, Double>();
		
		/*Do to the lack of a defined method for finding the size Range for Bins or the number of Bins
		 * best suited for a histogram table. I have decided to make my own rules for scaling bins:
		 * Find the smallest size of bins that create 18 or fewer bins
		 * The Bin size must be between 4 and 1/5 of the maximum value of the array 
		 */
		
		for(int i=4; i<(file.getmax()/5); i++) {//this is here to save memory. so where not going through the loop 50 times.
			int count=(int) ((file.getmax())/i);
			if (count<=18) {
				System.out.println("There will be "+(count+1)+" bins. Each bin has "+i+" values.");
				sourceMap=buildHisto(file, i);//populate the table with the array and dynamic bin size
				break;//this is important. without this the loop starts again
			}
		}
		
		// Copy all data from hashMap into the LinkedHashMap and sort the map into numerical order, and a print statement
		displayMap=SortStingsNumericly(sourceMap);
		
		return displayMap;
	}
	
	/*This builds the Histogram Table in 2 steps first generate the bin sizes and set them all to 0.
	 * Then compare the closest whole number equivalent to each value stored in the bins to find the closest match*/
	public static Map<List<Integer>, Integer> buildHisto(Reports file, int width) { //pass in length of bins and file
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
	
	//use the bubble sort technique to sort a list from lo9w to high or high to low. to save on ram the original list is lost
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
	
	/*sort a hash map list by making an array out of the first value of each of the bins.  Then sorting that array.
	 *Then building a linked hash map by matching each bin with each value of the sorted array*/
	public static Map<String, Double> SortStingsNumericly(Map<List<Integer>, Integer> map) {
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