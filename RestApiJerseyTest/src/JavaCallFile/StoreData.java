package JavaCallFile;

import javaDemo.Reports;

public class StoreData {
	 public double[][] Information;
	 public Reports[][] box;
	 public Reports[] record;
	 
	 public double[][] getInformation() {return this.Information;}//Retrieve a value
	 public void setreportId(double[][] info) {this.Information = info;}// save a value
	    
	 public Reports[][] getReports() {return this.box;}
	 public void setReports(Reports[][] bx) {this.box = bx;}
}
