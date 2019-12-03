package com.webbuild.javabrains.javaDemo.Junit;

import java.util.Arrays;
import java.util.Scanner;

import com.webbuild.javabrains.Operations.SwitchBoard;
import com.webbuild.javabrains.model.Reports;

public class WraperClassInput {
	public static Reports[] TestInterFace(double[][] input, String name, Scanner useinput) {
		//declare the needed variables
		Reports[] rep = new Reports[2];//declare an array of objects from class Reports
		int count=0, k=0;
		String userinput, exitChar, newArr;
		while(true) {
			rep[count] =  new Reports(); //create new report
			//Report ID is a fixed value and Reads Filename-report number.array number
			rep[count].setreportId(name+"-"+(count+1)+"."+k); //unique flag for report
			
			//prompt user for The desired fields they wish to populate the object with
			System.out.println("what operation do you wish to preform on array "+rep[count].getreportId()
							+": \n"+ Arrays.toString(input[k]) + "\n"
							+"you can preform these operations:SortHi, SortLo, Average, Median, Mode, Min, Max");
			userinput = useinput.nextLine(); // Scans the next token of the input as a String.
			String[] inputary=userinput.split(" |, |,"); //use multi or statement to account for human error
			
			//run the build reports method
			SwitchBoard.buildReports(rep[count], inputary, input[k]);//Switch statement separated for better integration with web content
			
			System.out.println("do you wish to continue? y/n");
			exitChar = useinput.nextLine();
			if(exitChar.contentEquals("n")) { //end path option
				System.out.println("Reports Compiled"+"\n");
				break;
			}else if(exitChar.contentEquals("y")){
				count++; //Auto create a new report ID
				if(count<rep.length) {//check to see if more objects can be stored
					System.out.println("do you wish to continue to work with this array? y/n"); 
					newArr = useinput.nextLine();
					if(newArr.contentEquals("y")) {
						System.out.println("\n");
					}else { //change the array variable to work with a different array
						k++;
						System.out.println("\n");
					}
					
				}else {// user Error handling
					System.out.println("Report is at capacity. No more files can be stored. Exiting program.");
					break;
				}	
			}else {count++; if(k>0) {k--;}}//Decrementing the array value
		}
		return rep;
	}
	
}
