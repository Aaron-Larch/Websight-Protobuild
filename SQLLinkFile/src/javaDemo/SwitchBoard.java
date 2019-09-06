package javaDemo;

import java.util.Arrays;


public class SwitchBoard {
	static Store scan=new Store();
	
	//This is a Java interface to run through all of the properties for populating an object for testing purposes In the Java console.
	public static Reports[] JavaInterFaceBuildObject(double[][] input, String name) {
		//declare the needed variables
		Reports[] rep = new Reports[10];//declare an array of objects from class Reports
		int count=0, k=0;
		char exitChar, newArr;
		String userinput;
		while(true) {
			rep[count] =  new Reports(); //create new report
			//Report ID is a fixed value and Reads Filename-report number.array number
			rep[count].setreportId(name+"-"+(count+1)+"."+k); //unique flag for report
			
			//prompt user for The desired fields they wish to populate the object with
			System.out.println("what operation do you wish to preform on array "+rep[count].getreportId()
							+": \n"+ Arrays.toString(input[k]) + "\n"
							+"you can preform these operations:SortHi, SortLo, Average, Median, Mode, Min, Max");
			userinput = scan.words(); // Scans the next token of the input as a String.
			String[] inputary=userinput.split(" |, |,"); //use multi or statement to account for human error
			
			//run the build reports method
			buildReports(rep[count], inputary, input[k]);//Switch statement separated for better integration with web content
			
			/*Run a small logic tree to provide the user with a variety of options
			 * do you want to create a new object?
			 * do you want to use a new array?
			 * do you want to use the previous array?
			 * Can you make any more objects?
			 */
			System.out.println("do you wish to continue? y/n");
			exitChar = scan.symbol();
			if(exitChar=='n') { //end path option
				System.out.println("Reports Compiled"+"\n");
				break;
			}else if(exitChar=='y'){
				count++; //Auto create a new report ID
				if(count<rep.length) {//check to see if more objects can be stored
					System.out.println("do you wish to continue to work with this array? y/n"); 
					newArr = scan.symbol();
					if(newArr=='y') {
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
	
	/*The Reports object has 8 fields to populate. the user should be able to populate
	 * any combination of fields at any time by selecting any or all of the presented options
	 * the code dose this by putting a Switch statement inside a for loop and turns the user section 
	 * from a string into an array. 
	 */
	
	public static void buildReports(Reports rep, String[] inputary, double[] input) {
		String inputOP;
		for(int i=0; i < inputary.length; i++) {
			
			/*do to data retention causing all stored array object to all be set to the value of the latest entry 
			 * A temporary array was created to fix the problem by dropping all stored values from the cash*/
			
			double[] temp= new double[input.length]; //refresh temporary array
			for(int j=0; j<temp.length; j++) {temp[j]=input[j];} //populate temp array with input array
			inputOP=inputary[i];
		rep.setoriginal(temp); //save original Data for backup purposes 	
		switch(inputOP.toLowerCase()){ //match inputary[i] with it's matching Switch case
		case"sorthi":
			//call operation and print result 
			System.out.print("Your Data was sorted high low and now reads: " + Arrays.toString(Statistics.sortEX(temp, "hi"))+"\n");
			rep.sethighC(temp); //save value in object
			break;
			
		case"sortlo":
			//call operation and print result 
			System.out.print("Your Data was sorted low high and now reads: " + Arrays.toString(Statistics.sortEX(temp, "lo"))+"\n");
			rep.setlowC(temp); //save value in object
			break;
			
		case"average":
			rep.setaverage(Statistics.average(temp)); //save value in object
			//call operation and print result 
			System.out.printf("The average of your Data is: %.4f%n", rep.getaverage());
			break;
			
		case"median":
			rep.setmedian(Statistics.median(temp));//save value in object
			//call operation and print result 
			System.out.printf("The median of your Data is: %.1f%n", rep.getmedian());
			break;
			
		case"mode":
			rep.setmode(Statistics.mode(temp));//save value in object
			//call operation and print result
			System.out.print("The mode(s) of your Data is: " + rep.getmode() + "\n");
			break;
			
		case"max":
			rep.setmax(Statistics.minMax(temp, inputOP)); //save value in object
			//call operation and print result
			System.out.print("The highest posibule value of your Data is: " + rep.getmax() + "\n");
			break;
			
		case"min":
			rep.setmin(Statistics.minMax(temp, inputOP)); //save value in object
			//call operation and print result
			System.out.print("The lowest posibule value of your Data is: " + rep.getmin() +"\n");
			break;
			
		default:
			//User Error handling
			System.out.println(inputOP + " appears to be an invalid operation. This report has been reset.");
			rep=null;
			i = inputary.length;
			break;
			}
		}
	}
}