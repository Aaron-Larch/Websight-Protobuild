package javaDemo;

import java.util.Arrays;
import java.util.List;

public class Reports {
    private double[] highcount;
    private double[] lowcount;
    private String reportID;  //primary key value
    private double max;
    private double min;
    private double ave;
    private double mid;
    private List<Double> mode;

    public String getreportId() {return this.reportID;}//Retrieve a value
    public void setreportId(String rID) {this.reportID = rID;}// save a value
    
    public double[] gethighC() {return this.highcount;}
    public void sethighC(double[] hiC) {this.highcount = hiC;}
    
    public double[] getlowC() {return this.lowcount;}
    public void setlowC(double[] loC) {this.lowcount = loC;}
    
    public double getmax() {return this.max;}
    public void setmax(double mx) {this.max = mx;}
    
    public double getmin() {return this.min;}
    public void setmin(double mn) {this.min = mn;}
    
    public double getaverage() {return this.ave;}
    public void setaverage(double avg) {this.ave = avg;}
    
    public double getmedian() {return this.mid;}
    public void setmedian(double md) {this.mid = md;}
    
    public List<Double> getmode() {return this.mode;}
    public void setmode(List<Double> mod) {this.mode = mod;}
    
    public void showRecord() { //print all saved values at once
    	if(reportID!=null) { //check to see if value exists before printing
    		System.out.print("The name given to this record is: "+reportID+"\n");
    		
    		if(highcount!=null) {
    			System.out.print("your record was sorted high low and now reads " + Arrays.toString(highcount)+"\n");
    		}
    		
    		if(lowcount!=null) {
    			System.out.print("your record was sorted low high and now reads " + Arrays.toString(lowcount)+"\n");
    		}
    		
    		if(ave!=0) {
    			System.out.printf("the average of your record is: %.4f %n", ave);
    		}
    		
    		if(mid!=0) {
    			System.out.printf("the median of your record is: %.1f %n", mid);
    		}
    		
    		if(mode!=null) {
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