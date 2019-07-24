/**
 * 
 */
package scrapCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author gce
 *
 */
public class javaTestRun {

	static Scanner scan = new Scanner(System.in); // Reading from System.in

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String yes = "yes";
		double sum;
		System.out.println("Enter the name of Your Array:");
		String name = scan.next(); // Scans the next token of the input as a String.
		System.out.println("Enter the size of Your Array:");
		int size = scan.nextInt(); // Scans the next token of the input as an int.
		System.out.println("Enter the upper range of Your Array:");
		int range = scan.nextInt(); // Scans the next token of the input the range of random numbers.
		// once finished
		int[] array = {1,1,1,2,2,2,3,3,3,4,4,4};
		List<Integer> temp;
		//array = createArray(size, range);
		array = sort(array, "lo");
		System.out.print("you list was sorted high low and now reads " + Arrays.toString(array)+"\n");
		System.out.print("This is your array " + name + ". This array has " + size
				+ " enterys ant the largest number is " + range + "\n" + name + ": " + Arrays.toString(array) + "\n");
		System.out.println("do you want to sort " + name + ":");
		String choice = scan.next(); // Scans the next token of the input as a String.
		if (yes.contentEquals(choice)) {
			System.out.println("do you want to sort high number to low number?");
			String order = scan.next(); // Scans the next token of the input as a String.
			array = sort(array, order);
			System.out.print("you list was sorted high low and now reads " + Arrays.toString(array));
		}
		sum = average(array);
		System.out.print("\n" + "the average of your list is: " + sum + "\n");
		sum = median(array);
		//System.out.print("the median of your list is: " + sum + "\n");
		temp = mode(array);
		System.out.print("the mode of your list is: " + temp + "\n");
		sum = minMax(array, "max");
		System.out.print("the hihest value of your list is: " + sum + "\n");
		sum = minMax(array, "min");
		System.out.print("the lowest value of your list is: " + sum + "\n");
		FrequencyTable(array);
	}

	public static int[] sort(int[] array, String order) {
		int temp;
		if (order.contentEquals("hi")) {
			// sort highest value to lowest value
			for (int i = 0; i < array.length; i++) {
				for (int j = i + 1; j < array.length; j++) {
					if (array[i] < array[j]) {
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
		} else {
			// sort lowest value to highest value
			for (int i = 0; i < array.length; i++) {
				for (int j = i + 1; j < array.length; j++) {
					if (array[i] > array[j]) {
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}

			}
		}
		return array;
	}

	public static int[] createArray(int size, int range) {
		Random rand = new Random();
		int[] array = new int[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(range);
		}
		return array;
	}

	public static double average(int[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}
		sum = sum / array.length;
		return sum;
	}

	public static int minMax(int[] array, String choice) {
		int minMax;
		int[] temp;
		if (choice.equalsIgnoreCase("max")) {
			temp = sort(array, "hi");
			minMax = temp[0];
			return minMax;
		} else {
			temp = sort(array, "lo");
			minMax = temp[0];
			return minMax;
		}
	}

	public static double median(int[] array) {
		double median;
		double n1;
		double n2;
		int temp;
		if ((array.length + 1) % 2 == 0) {
			array = sort(array, "lo");
			temp = (array.length + 1) / 2;
			median = array[temp - 1];
			return median;
		} else {
			array = sort(array, "lo");
			temp = array.length / 2;
			n1 = array[temp - 1];
			n2 = array[temp + 1];
			median = (n1 + n2) / 2;
			return median;
		}

	}

	public static List<Integer> mode(int[] array) {
		List<Integer> mode = new ArrayList<Integer>();
		array=sort(array, "lo");
		int maxCount = 0;
		for (int i = 0; i < array.length; i++) {
			int count = 0;
			for (int j = 0; j < array.length; j++) {
				if (array[j] == array[i]) {
					count++;
				}
			}
			if (count > maxCount) {
				maxCount = count;
				mode.clear();
				mode.add(array[i]);
			} else if (count == maxCount && mode.contains(array[i])==false) {
				mode.add(array[i]);
			}
		}
		return mode;
	}
	
	public static void Range(double mid, int[] array) {
		// TODO Auto-generated constructor stub
		double LIQR, UIQR;
		int h=0;
		int[] Q1=new int [(array.length/2)];
		int[] Q3=new int [(array.length/2)];
		//range = file.getmax()-file.getmin();
		if(array.length%2==0) {
		for(int i=0; i<array.length; i++) {
			if(array[i]<=mid || h < Q1.length) {
				Q1[h]=array[i];
				h++;
			}else {
					Q3[i-h]=array[i];
			}
		}
		}else {
			for(int i=0; i<array.length; i++) {
				if(array[i]==array[(array.length/2)]) {
					i++;
					h++;
				}
				if(array[i]<= mid || h< Q1.length) {
					Q1[h]=array[i];
					h++;
				}else {
					{Q3[i-h]=array[i];}
				}
			}
		}
		System.out.println(Arrays.toString(Q1));
		System.out.println(Arrays.toString(Q3));
		LIQR=median(Q1);// lower interquartile range
		UIQR=median(Q3);// upper interquartile range
		//System.out.println("min: "+file.getmin());
		System.out.println("lower interquartile range: "+LIQR);
		System.out.println("median: "+mid);
		System.out.println("upper interquartile range: "+UIQR);
		//System.out.println("max: "+file.getmax());
		System.out.println("interquartile range: "+(UIQR-LIQR));
		//all that is needed for a whisker box chart
		//System.out.println("range: "+range);
		
	}
	public static void FrequencyTable(int[] arr) {
		HashMap<Integer,Integer> h = new HashMap<Integer,Integer>();
		for(int i=0; i<arr.length; i++){
            if(h.containsKey(arr[i])){
                h.put(arr[i], h.get(arr[i]) + 1);
            } else {
                h.put(arr[i], 1);
            }
        }
        System.out.println(h);
	}
}
