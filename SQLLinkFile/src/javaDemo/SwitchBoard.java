package javaDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SwitchBoard {
	static Store scan=new Store();
	
	public static Reports[] StatbuildEX10(double[][] input, String name) {
		Reports[] rep = new Reports[10];//declare an array of objects from class Reports
		int count=0, k=0;
		char exitChar, newArr;
		String userinput;
		while(true) {
			rep[count] =  new Reports(); //create new report
			//Report ID Reads Filename-report number.array number
			rep[count].setreportId(name+"-"+(count+1)+"."+k); //unique flag for report
			System.out.println("what operation do you wish to preform on array "+rep[count].getreportId()
							+": \n"+ Arrays.toString(input[k]) + "\n"
							+"you can preform these operations:SortHi, SortLo, Average, Median, Mode, Min, Max");
			userinput = scan.words(); // Scans the next token of the input as a String.
			String[] inputary=userinput.split(" |, |,"); //use multi or statement to account for human error
			buildReports(rep[count], inputary, input[k]);//Switch statement separated for better integration with web content
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
					System.out.println("Report is at capasity. No more files can be stored. Exiting program.");
					break;
				}	
			}else {count++; if(k>0) {k--;}}//Decrementing the array value
		}
		return rep;
	}
	
	public static void SearchEngein(Reports[][] file) { //user interface method
		Reports[] output=new Reports[10]; //create a smaller composite array
		int count=0;
		System.out.println("What files do you want to look at? If you want to look at every file type all.");
		String Record = scan.words(); //file choice
		System.out.println("what paramiter do you want to sort by? Name, Average, Median, Mode, Min, Max"+"\n"
				+"Please Format your responce in the following format 'Average >= 20' or 'Name contains 3'");
		String userinput = scan.words(); //user search requirements submited as a string 
		Reports[] resultes=search(file, userinput, Record); //run the search
		if(resultes[0].getreportId().equalsIgnoreCase("flag")) //user error handling what to return if the result is zero
		{System.out.println("There are no records matching your query");}
		else{for(int i=0; i<resultes.length; i++) { //print the results
			if(resultes[i]!=null) {
				resultes[i].showRecord();//call the print method
				System.out.println("do you want to work further with this file?");
				String choice = scan.words(); // Scans the next token of the input as a String.
				if ("yes".equalsIgnoreCase(choice)|"y".equalsIgnoreCase(choice)) {
					output[count]= new Reports(); //create a new object
					output[count]=resultes[i]; //populate object
					count++;
				}
			}
		}
		BuildPath.complexOps(output); //run secondary calculations
		}
	}
	
	public static Reports[] search( Reports[][] file, String userinput, String Record) { 
		//create a results record
		Reports[] storedval = null;
		String[] feild= new String[3];
		String[] inputary=userinput.split(" "); //split the sentence into relevant pieces  
		
		//change format to meet method parameters 
		if(inputary.length==3) {feild=inputary;}//check to see if the format is already there
		else {
			feild[0]=inputary[0]; //the first value is always the operation we wish to s
			feild[1]=inputary[1]; //Set the Value to the first word you wish to combine
			for(int i=2; i<inputary.length-1; i++) {feild[1]+=" "+inputary[i].toString();}//concainate the rest of the phrase into one array value
			feild[2]=inputary[inputary.length-1]; //the last variable is the value 
		}
		
		switch(feild[0].toLowerCase()){
		case"name":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getreportId", Record, feild[1].toLowerCase(), feild[2]);
			break;
		
		case"mean":
		case"average":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getaverage", Record, feild[1], feild[2]);
			break;
	
		case"median":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getmedian", Record, feild[1], feild[2]);
			break;
	
		case"mode":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getmode", Record, "List", feild[2]);
			break;
	
		case"max":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getmax", Record, feild[1], feild[2]);
			break;
	
		case"min":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=BuildPath.searchAllFiles(file, "getmin", Record, feild[1], feild[2]);
			break;
	
		default:
			// user error handling
			System.out.println(userinput + " appears to be an invalid operation. This report has been reset.");
			break;
		}
		return storedval;
	}
	
	public static void buildReports(Reports rep, String[] inputary, double[] input) {
		String inputOP;
		for(int i=0; i < inputary.length; i++) {
			
			double[] temp= new double[input.length]; //refresh temporary array
			for(int j=0; j<temp.length; j++) {temp[j]=input[j];} //populate temp array with input array
			inputOP=inputary[i];
			
		switch(inputOP.toLowerCase()){ //match inputary[i] with it's matching Switch case
		case"sorthi":
			//call operation and print result 
			System.out.print("you list was sorted high low and now reads " + Arrays.toString(Statistics.sortEX(temp, "hi"))+"\n");
			rep.sethighC(temp); //save value in object
			break;
			
		case"sortlo":
			//call operation and print result 
			System.out.print("you list was sorted low high and now reads " + Arrays.toString(Statistics.sortEX(temp, "lo"))+"\n");
			rep.setlowC(temp); //save value in object
			break;
			
		case"average":
			rep.setaverage(Statistics.average(temp)); //save value in object
			//call operation and print result 
			System.out.printf("the average of your list is: %.4f %n", rep.getaverage());
			break;
			
		case"median":
			rep.setmedian(Statistics.median(temp));//save value in object
			//call operation and print result 
			System.out.printf("the median of your list is: %.1f %n", rep.getmedian());
			break;
			
		case"mode":
			rep.setmode(Statistics.mode(temp));//save value in object
			//call operation and print result
			System.out.print("the mode(s) of your list is: " + rep.getmode() + "\n");
			break;
			
		case"max":
			rep.setmax(Statistics.minMax(temp, inputOP)); //save value in object
			//call operation and print result
			System.out.print("the highest value of your list is: " + rep.getmax() + "\n");
			break;
			
		case"min":
			rep.setmin(Statistics.minMax(temp, inputOP)); //save value in object
			//call operation and print result
			System.out.print("the lowest value of your list is: " + rep.getmin() +"\n");
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

	public static boolean Operaton(Object obj, String input, String opr) {
		//convert a user input into a format java can understand
		boolean flag = false; //set the return value to see if the comparison is true
		double value = Double.parseDouble(opr.trim()); //convert user input into a double
		switch(input.toLowerCase()){
		case"<":
		case"less than":
			flag=((double)obj<value); //check values
			break;		
			
		case">":
		case"grater than":
			flag=((double)obj>value); //check values
			break;
		
		case"=":
		case"equal to":
		case"equals":
			flag=((double)obj==value); //check values
			break;
		
		case">=":
		case"Grater than or equal to":
			flag=((double)obj>=value); //check values
			break;	
		
		case"<=":
		case"less than or equal to":	
			flag=((double)obj<=value); //check values
			break;
		
		case"!=":
		case"dose not equal":	
			flag=((double)obj!=value); //check values
			break;
			
		case"contains":
			flag=(obj.toString().contains(opr)); //strings are different so require a different state
			break;
			
		case"of":
		    List<Double> list = new ArrayList<Double>(); //create a temp mode variable 
		    if (obj.getClass().isArray()) { 
		        list = Arrays.asList((Double[])obj); //populate array
		        flag=(list.contains(value)); //check values
		    }else {flag=false;} //check to see if record has a mode value
			break;	
			
		default:
			//user error handling
			System.out.println(input+" appears to be an invalid operation. Please try again");
			break;
		}
		return flag;
	}
}