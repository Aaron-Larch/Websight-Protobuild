package com.webbuild.javabrains.Operations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.webbuild.javabrains.Store;
import com.webbuild.javabrains.model.Reports;

public class SimpleSerch {
	//the Library is split into 5 zones each one representing one or the fields needed to perform the search for better processing
	private static String[][] Library= {
			 {"Average","Mean","Median","Mode","Min","Max","Name","report id","record name","primary key"},
			 {">","<","=","!=","<=",">=","contains","of","less than","Greater than","equal to",
			  "less than or equal to","Greater than or equal to","does not equal","equals", "equal", "contains the number"},
			 {"\\d*\\.?\\d+"}, //use RegX to make sure that the user only entered a double or an integer
			 {"Error", "ErrCollume", "ErrCompair", "ErrValue", "ErrOrder"},//Check value for incomplete statements or invalid statements
			 {"primary", "record", "report", "id", "key", "name"},
			 {"primary key", "record name", "report id", "name"}//bank of words that must pair with precise actions
			};
	static Store scan=new Store();
	
	//This is a Java interface to run through all of the properties of the search method for testing purposes In the Java console.
	public static void JavaInterFaceSearch(Reports[][] file) { //user interface method
		//set the needed variables for the method
		Reports[] output=new Reports[10]; //create a smaller composite array
		int count=0;
		
		//Prompt the user to generate the secondary set of inputs namely the arrays they want to search and the search parameters 
		System.out.println("What files do you want to look at? If you want to look at every file type all.");
		String Record = scan.words(); //file choice
		System.out.println("what parameter do you want to sort by? Name, Average, Median, Mode, Min, Max"+"\n"
				+"Please Format your responce in the following format 'Average >= 20' or 'Name contains 3'");
		String userinput = scan.words(); //user search requirements submitted as a string 
		
		Reports[] resultes=search(file, userinput, Record); //run the search to find all matching objects and store them in an array
		
		//after the Search is completed check for flag values to catch incomplete user inputs and search results that return nothing
		if(resultes[0].getreportId().equalsIgnoreCase("flag")) //user error handling what to return if the result is zero
		{System.out.println("There are no records matching your query");}
		else if(resultes[0].gethighC()==null) //user error handling to catch incomplete user request
		{System.out.println(userinput+" Is an Incompete statement the I cannot act upon");}
		
		//Take an array of objects and have the user create a custom report by 
		//choosing witch of the returned search results to perform secondary operations on
		else{
			for(int i=0; i<resultes.length; i++) { //print the results
			resultes[i].showRecord();//call the print method
			System.out.println("do you want to work further with this file?");
			String choice = scan.words(); // Scans the next token of the input as a String.
			if ("yes".equalsIgnoreCase(choice)|"y".equalsIgnoreCase(choice)) {
				output[count]= new Reports(); //create a new object
				output[count]=resultes[i]; //populate object
				count++;
			}
		}
		BuildPath.complexOps(output); //run secondary calculations
		}
	}
	
	//run a search operation that will take a users input string "Find All Objects that have a median value greater than 12"
	//and turn that string into something java can understand
	public static Reports[] search( Reports[][] file, String userinput, String Record) { 
		//create a results record
		Reports[] storedval = null;
		
		/*The goal of the section of code is to create a logic operation that will format the user's input string into a 
		 * form that the search engine will recognize to do this the The code will change the input string into
		 * an array counting by the spaces in the string if the arrays length is less than 3 that means there has 
		 * been an error in the code and the flag value is thrown.
		 * if the arrays length is equal to 3 then the format checks out and the code can precede as normal.
		 * if the arrays length is greater the 3 the code will assume that you are using complete words instead of symbols
		 * for your search and format the string into 3 parts by storing the fist and last values and concatenating the
		 * rest back into a string for the final value.
		 */
		
		String[] feild=dynamicparse(userinput);
		
		/*By using a Switch operation the code can quickly look for a matching value from a library of stored string values
		 *once a match is found the code will assine the method call that will fetch the required search parameter to the string a value
		 *and run a method to do a comparison operation with the given guidelines stated in the rest of the user search statement
		 */
		
		switch(feild[0].toLowerCase()){
		case"record name":
		case"primary key":
		case"report id":
		case"name":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getreportId", Record, feild[1].toLowerCase(), feild[2]);
			break;
		
		case"mean":
		case"average":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getaverage", Record, feild[1], feild[2]);
			break;
	
		case"median":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getmedian", Record, feild[1], feild[2]);
			break;
	
		case"mode":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getmode", Record, feild[1], feild[2]);
			break;
	
		case"max":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getmax", Record, feild[1], feild[2]);
			break;
	
		case"min":
			//Required search materials are: the object you are searching, the value you wish to retrieve, the primary key, the operation, and the parameter
			storedval=searchFiles(file, "getmin", Record, feild[1], feild[2]);
			break;
	
		default:
			// user error handling
			storedval= new Reports[1]; //make a new object
			storedval[0]=new Reports();
			storedval[0].setreportId(ErrorMessage(feild[0]));
			break;
		}
		return storedval;
	}
	
	/*This method brakes the search statement down into four basic parts
	 * The field or primary key value that is set up for each object or use the key word all to ignore the primary key all together
	 * All searches are a comparison statement and to search for a single aspect of an object with multiple aspects the code 
	 * will change getter and setter values into a dynamic function set to a string value with the getDeclaredMethod method.  
	 * The search statement is a string and so is the >, <, =, ect. operations those will need to be changed into something java can read
	 * The fourth field is the number value, this must be entered as 3 or 4 instead or three of four. the reson is that the code is easer
	 * to work with if we force the user to stick to numbers. 
	 */
	
	private static Reports[] searchFiles(Reports[][] file, String name, String field1, String sort, String field2 ) {
		ArrayList<Reports> tmp = new ArrayList<Reports>(); //use this to create an array of dynamic size
		Reports[] array=new Reports[1];
		Method val;
		for(int i=0; i < file.length; i++) { //search all available arrays
			//search for a single primary key array or search trough all arrays
			if(file[i] != null && (file[i][0].getreportId().contains(field1) || field1.equalsIgnoreCase("all"))) {
				//Search through the array of objects 
				for(int j=0; j < file[i].length; j++) {
					try {
						if(file[i][j] != null) {//Catch statement to remove 'empty' values
							
							//Convert the Statement 'Average > 20'  into String, String, String then convert to Object, String, String
							val = file[i][j].getClass().getDeclaredMethod(name); //declare a reflected method
							Object space = val.invoke(file[i][j]); //use invoke to execute the method, and store the results in an object
							
							//run a dynamic comparison operation determined by the user statement
							if(Operaton(space, sort , field2)) {
								tmp.add(file[i][j]); //build a result file
							}	
						}
					
					//catch statements requierd to run .getDeclaredMethod() and .invoke() methods
					} catch (NoSuchMethodException | SecurityException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						System.out.println("This Method cannot be accesed or dose not exist");
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(tmp.isEmpty()) { //check to see if the result returns empty. set flag value if true.
			array[0] =  new Reports();
			array[0].setreportId("flag");
			return array;
		}else {
			Reports[] statement = tmp.toArray(new Reports[tmp.size()]); //convert into an array of objects
			return statement;
		}	
	}
	
	//use a switch statement to Create a dynamic comparison statement from a user string into a boolean true/false test
	private static boolean Operaton(Object obj, String input, String opr) {
		//Convert the Statement Object, String, String into Object, String, Double
		boolean flag = false; //set the return value to see if the comparison is true
		double value = Double.parseDouble(opr.trim()); //convert user input into a double
		
		if(obj != null) { //test for a populated field
		//use the remaining string in a switch statement to find the correct comparison from a library of operations 
		switch(input.toLowerCase()){
		case"<": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"less than":
			flag=((double)obj<value); //check values
			break;		
			
		case">": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"greater than":
			flag=((double)obj>value); //check values
			break;
		
		case"=": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"equal to":
		case"equals":
		case"equal":
			flag=((double)obj==value); //check values
			break;
		
		case">=": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"greater than or equal to":
			flag=((double)obj>=value); //check values
			break;	
		
		case"<=": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"less than or equal to":	
			flag=((double)obj<=value); //check values
			break;
		
		case"!=": //with multiple cases per statement the code can account for synonyms and alternate wordings
		case"does not equal":	
			flag=((double)obj!=value); //check values
			break;
			
		case"contains":
		case"contains the number":
			flag=(obj.toString().contains(opr)); //strings are different so require a different state
			break;
			
		case"of": //Exclusive case for modes sense there can be multiple modes to a set of data a list contains operation is required
		    flag=(obj.toString().contains(opr)); //check values
			break;	
			
		default:
			//user error handling
			System.out.println(input+" appears to be an invalid operation. Please try again");
			break;
		}
		}else {flag=false;} //check to see if record has a mode value
		return flag;
	}
	
	//Error message library so that each error type has its own error code message
	public static String ErrorMessage(String input) {
		switch(input){
		case"Error": return "This Is not a Statement that I understand";
		case"ErrCollume": return "The Field you are trying to serch for dose not exist. \nPlease Try Again";
		case"ErrCompair": return "I Do not Know how to preform this operation. Please try again";
		case"ErrValue": return "Sorry but this is not a value i understand. I can only read Number values";
		case"ErrOrder": return "The compairson you'r trying to make is useing a reserved word and can not be preformed";
		default: return "";
		}
	}
	
	//a remote call for the parse string operation from the search method
	public static String[] dynamicparse (String words) {
		String[] feild= new String[3];
		String[] inputary=words.split(" "); //split the sentence into relevant pieces  
		
		//change format to meet method parameters 
		if(inputary.length < 3) {feild[0]="Error";}
		else {
			int count=0;
			for(int i=0; i<feild.length; i++) {
				feild[i]=inputary[count]; //the first value is always the operation we wish to search for
				count ++;//By setting count equal to the total number of strings we can have the first and last entry use the same line of code
				if(i==0) {
					//Check the second position of the array to see if the value matches any of the spatial stored words in the library
					for(int j=0; j<Library[4].length; j++) {
						if(inputary[count].equalsIgnoreCase(Library[4][j])) {
							feild[i]+=" "+inputary[count].toString();
							count ++;
						}
					}
					//check to see if the word selected is a recognized word from the library
					if(check(feild[0], 0)==false) {feild[0]="ErrCollume"; break;} //if error found end loop
					
				}else if(i==1) {
					//collect all but the last stored array value to construct the comparison statement
					for(int j=count; j<inputary.length-1; j++) {
						feild[i]+=" "+inputary[j].toString();
						count++;
					}
					//check to see if the word selected is a recognized word from the library
					if(check(feild[1], 1)==false) {feild[0]="ErrCompair"; break;} //if error found end loop
					
					//Some Words Must require a unique paring of field 1 and field 2 values
					for(int jj=0; jj < Library[5].length; jj++) {
						if(feild[0].equalsIgnoreCase(Library[5][jj])) { //Check to see if the stored word is a word that requires a paring
							if(feild[1].equalsIgnoreCase("contains")==false && feild[1].equalsIgnoreCase("contains the number")==false)
								 {feild[0]="ErrOrder"; break;} //if error found end loop
							else {break;} //if there is a match then there is no need to go through the rest of the code
							}
							else if(jj==Library[5].length-1) {//Check to see if the mode is paired with its unique identifier
								if(feild[0].equalsIgnoreCase("mode")) {
									if(feild[1].equalsIgnoreCase("of")==false) {feild[0]="ErrOrder"; break;} //if error found end loop
									else {break;}
								//make sure that that that any of the recognized words are not using a reserved word
								}else if(feild[1].equalsIgnoreCase("contains")==true 
										|| feild[1].equalsIgnoreCase("contains the number")==true 
										|| feild[1].equalsIgnoreCase("of")==true) 
								{feild[0]="ErrOrder"; break;}//if error found end loop
							}
					}
					if(feild[0].equalsIgnoreCase("ErrOrder")) {break;} //when error is found there is no need to continue the code
				}else if(i==2) {
					//check to see if the final value is a number value that is accepted by the library
					if(feild[2].matches(Library[2][0].toString())==false) {feild[0]="ErrValue";}
				}
			}
		}
		return feild;
	}
	
	public static boolean check(String feild, int i) {
		//Check word against all excepted words to prevent misspellings and other user errors
		for(int ii=0; ii < Library[i].length; ii++) {
			if(feild.equalsIgnoreCase(Library[i][ii])) {return true;}
			else if(ii==Library[i].length-1) {return false;} //if no match is found by the end of the library then we throw false
		}
		return false;
	}
}
