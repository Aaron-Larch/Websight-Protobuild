package javaDemo;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildPath {
	public static Store scan=new Store();
	public static BuildPath Build = new BuildPath();
	
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
			System.out.print("This is your array " + name + ". This array has " + Math.abs(size[i])
			+ " enteries and the largest number is " + range[i] + "\n" + name + ": " + Arrays.toString(arrayBox[i]) + "\n");
		}
		return arrayBox;
	}
	
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
	
	public Reports[] BuildRecord(int[] length, int[] random ){
		System.out.println("Enter the name of Your Array:");
		String name = scan.words(); // Scans the next token of the input as a String.
		double[][] array =Build.arrayBoxInt(length,random, name);//create a double array
		Reports[] box=SwitchBoard.StatbuildEX10(array,name);//create and populate an array of objects
		return box;
	}
	
	public  Reports[][] buildNetwork(int[] length, int[] random){
		Reports[][] box =new Reports[3][]; //this value must match the Switch value or else error
		for(int i=0; i<box.length; i++) {
			System.out.println("Enter the name of Your Array:");
			String name = scan.words(); // Scans the next token of the input as a String.
			double[][] array =Build.arrayBoxInt(length,random, name); //create a double array
			System.out.println("do you want to analize " + name + ":");
			String choice = scan.words(); // Scans the next token of the input as a String.
			if ("yes".equalsIgnoreCase(choice)|"y".equalsIgnoreCase(choice)) 
			{box[i]=SwitchBoard.StatbuildEX10(array,name);} //create and populate an array of objects
			else if("no".equalsIgnoreCase(choice)|"n".equalsIgnoreCase(choice)) {i--;} //by decrementing i the for loop becomes infinite  
			else {break;}//cut off point to ease testing
		}
		return box;
	}
	
	public static Reports[] searchAllFiles(Reports[][] file, String name, String field1, String sort, String field2 ) {
		ArrayList<Reports> tmp = new ArrayList<Reports>(); //use this to create an array of dynamic size
		Reports[] array=new Reports[1];
		Method val;
		for(int i=0; i < file.length; i++) { //search all available arrays
			//search for a single array or search trough all arrays
			if(file[i] != null && (file[i][0].getreportId().contains(field1) | field1.equalsIgnoreCase("all"))) {
				//Search through the array of objects 
				for(int j=0; j < file[i].length; j++) {
					try {
						if(file[i][j] != null) {
							val = file[i][j].getClass().getDeclaredMethod(name); //declare a reflected method
							Object space = val.invoke(file[i][j]); //use invoke to execute the method, and store the results in an object
							if(SwitchBoard.Operaton(space, sort , field2)) {
								tmp.add(file[i][j]); //build a result file
							}	
						}
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
			return statement;}	
	}
	
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
