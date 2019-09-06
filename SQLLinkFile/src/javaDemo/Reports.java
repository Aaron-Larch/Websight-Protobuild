package javaDemo;

import java.util.Arrays;
import java.util.List;

/*This is an object used to store an array and all related Statistical information about that array
 * The information that can be stored is
 * A Report ID, -This value is going to be the primary key of this object so i needs to be unique
 * The Array sorted from Highest value to the lowest value. -This is here to make finding the Max value easier to find
 * The Array sorted from Lowest value to the Highest value.
 * The Highest value of the array
 * The Lowest value of the array
 * the Mean value of the array
 * the median value of the array
 * The mode of an array
*/

public class Reports {
    private String reportID;  //primary key value
    private double[] originaldata;
    private double[] highcount;
    private double[] lowcount;
    private double max;
    private double min;
    private double ave;
    private double mid;
    private List<Double> mode;

    public double[] getoriginal() {return this.originaldata;}//Retrieve a value
    public void setoriginal(double[] orD) {this.originaldata = orD;}// save a value
    
    public String getreportId() {return this.reportID;}//Retrieve a value
    public void setreportId(String rID) {this.reportID = rID;}// save a value
    
    public double[] gethighC() {return this.highcount;}//Retrieve a value
    public void sethighC(double[] hiC) {this.highcount = hiC;}// save a value
    
    public double[] getlowC() {return this.lowcount;}//Retrieve a value
    public void setlowC(double[] loC) {this.lowcount = loC;}// save a value
    
    public double getmax() {return this.max;}//Retrieve a value
    public void setmax(double mx) {this.max = mx;}// save a value
    
    public double getmin() {return this.min;}//Retrieve a value
    public void setmin(double mn) {this.min = mn;}// save a value
    
    public double getaverage() {return this.ave;}//Retrieve a value
    public void setaverage(double avg) {this.ave = avg;}// save a value
    
    public double getmedian() {return this.mid;}//Retrieve a value
    public void setmedian(double md) {this.mid = md;}// save a value
    
    public List<Double> getmode() {return this.mode;}//Retrieve a value
    public void setmode(List<Double> mod) {this.mode = mod;}// save a value
    
    //this function will check the object to see which values have information and print every stored value of the object 
    public void showRecord() {
    	if(reportID!=null) { //check to see if value exists before printing
    		System.out.print("The name given to this record is: "+reportID+"\n");
    		
    		if(highcount!=null) { //check to see if value exists before printing
    			System.out.print("your record was sorted high low and now reads: " + Arrays.toString(highcount)+"\n");
    		}
    		
    		if(lowcount!=null) { //check to see if value exists before printing
    			System.out.print("your record was sorted low high and now reads: " + Arrays.toString(lowcount)+"\n");
    		}
    		
    		if(ave!=0) { //check to see if value exists before printing
    			System.out.printf("the average of your record is: %.4f %n", ave);
    		}
    		
    		if(mid!=0) { //check to see if value exists before printing
    			System.out.printf("the median of your record is: %.1f %n", mid);
    		}
    		
    		if(mode!=null) { //check to see if value exists before printing
    			System.out.print("the mode(s) of your record is: " + mode + "\n");
    		}
    		
    		if(max!=0) { //if you have a max value then odds are you have a min as well
			System.out.print("the highest value of your record is: " + max + "\n");
			System.out.print("the lowest value of your record is: " + min +"\n");
    		}
    	}else { //user error handling
    		System.out.print("Error: the record dose not Exist."+"\n");
    	}

    }
   
}