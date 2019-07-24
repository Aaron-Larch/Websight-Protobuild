package newfolder;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCode {

	public TestCode() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//Establish x as a variabule and creat a loop that increases that value by one
	Boolean decision1=false;
	//Define your variables 
	String word = "alpha";
	char letter = 'A';
	String newWord;
	StringBuilder wordTwo = new StringBuilder("alpha");
	//Did you want to just add the char to the string?
	newWord=word+letter;
	System.out.println(newWord);
	//did you want to replace matching values of the string
	word=word.replace(word.charAt(0), letter);
	System.out.println(word);
	//or did you want to replace the first array value with the char
	wordTwo.setCharAt(0, letter);
	System.out.println(wordTwo);
		for(int x=0; x<3; x++){
			int y=-1;
			System.out.println("y is: " +y);
			y=100;
			System.out.println("y is now: "+y);
		}
		

	int[][] array= {{1, 8, 5, 4},{2, 7, 3, 6}};
	//total number of array's and there elements
	System.out.println("There are " + array.length +" Total arrays each array has "+array[0].length+" values");
	//Sum both the total and the sum of each unit
	getSum(array);
	//the maximum of each array and for the total of all arrays
	getMaxValue(array);
	//the minimum of each array and for the total of all arrays
	getMinValue(array);
	//find the Average of each array and for the total average of all arrays
	getAverage(array);
	//count the total number of values across all arrays
	getTotal(array);
	String[][] strArray={{"one", "two", "three"},{"four", "five", "six"}};
	printArray(strArray);
	
	}
	
	public static void getSum(int[][] array) {
		//by declaring Total outside the for loop it will never reset and we can find the total for all the Arrays 
		int total=0;
		for(int i=0; i < array.length; i++) {
			//by declaring the sum inside the first for loop we can reset the sum to 0 for every pass of the loop
			int sum=0;
			for(int j=0; j < array[i].length; j++) {
				//because the sum value is reset for every loop only one value is needed to generate a unique statement for each array
				sum=sum+array[i][j];
			}
			//take the sum and add it to the running total of generated sums
			total=total+sum;
			//proof of sum
			System.out.print("the sum of sub array"+(i+1)+" is: "+sum+" ");
		}
		//proof of running total
		System.out.println(" the total sum of all arrays is: "+total);
	}
	
    public static void getMaxValue(int[][] array) {
        int maxValue = array[0][0];
        for (int j = 0; j < array.length; j++) {
        	int maxSubValue = array[j][0];
            for (int i = 0; i < array[j].length; i++) {
                if (array[j][i] > maxSubValue) {
                	maxSubValue = array[j][i];
                } 
                if (array[j][i] > maxValue) {
                	maxValue = array[j][i];
                } 
            }
           
            System.out.print("the Max Value of sub array"+(j+1)+" is: "+maxSubValue+" "); 
        }
        System.out.println("Maximum value of all arrays is: "+maxValue);
    }

    public static void getMinValue(int[][] array) {
        int minValue = array[0][0];
        for (int j = 0; j < array.length; j++) {
        	int minSubValue = array[j][0];
            for (int i = 0; i < array[j].length; i++) {
            	 if (array[j][i] < minSubValue) {
                 	minSubValue = array[j][i];
                 } 
                 if (array[j][i]< minValue) {
                 	minValue = array[j][i];
                 } 
            }
            System.out.print("the Min Value of sub array"+(j+1)+" is: "+minSubValue+" ");
        }
        System.out.println("Minimum value of all arrays is: "+minValue);
    }

    public static void getAverage(int[][] array) {
		//to print the remainder all values must be a double
    	double total=0;
		double avg;
		for(int i=0; i < array.length; i++) {
			double sum=0;
			for(int j=0; j < array[i].length; j++) {
				sum=sum+array[i][j];
			}
			avg=sum/array[0].length;
			total=total+sum;
			System.out.print("the Average of sub array"+(i+1)+" is: "+avg+" ");
		}
		//to find the total Average the program must find the total number of values across all arrays
		avg=total/(array.length*array[0].length);
		System.out.println("the total average of all arrays is: "+avg);
	}
    
    public static void getTotal(int[][] array) {
    	int count1= array.length;
    	int count2= array[0].length;
    	int sum =count2*count1;
    	for(int i=0; i < count1; i++) {
			for(int j=0; j < count2; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
    	System.out.println("the array is a "+count1+"X"+count2+ " and has a total of "+sum+" entries accross all arrays");
	}

     
    public static void printArray(String[][] array) {
    	for(int i=0; i < array.length; i++) {
			for(int j=0; j < array[i].length; j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
      
}
