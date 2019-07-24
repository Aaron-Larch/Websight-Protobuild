package Chapter7;

public class Vehicle implements Truck {
	private int passengers; //Number of passengers
	private int fuelCap; //fuel capacity in gallons
	private int mpg; //fuel consumption in miles per gallon
	
	//This is a constructor for Vehicle
	Vehicle(int p, int f, int m){
		passengers=p;
		fuelCap=f;
		mpg=m;
	}
	
	//return the range
	public int range() {
		return mpg*fuelCap;
	}
	
	//Compute fuel needed for a given distance
	public double fuelneeded(int miles) {
		return(double)miles/mpg;
	}
	
	// Accessory methods for instance variables
	public int getPassengers() {return passengers;}
	public void setPassengers(int p) {passengers=p;}
	public int getFuelcap() {return fuelCap;}
	public void setFuelcap(int f) {fuelCap=f;}
	public int getMpg() {return mpg;}
	public void setMpg(int m) {mpg=m;}
}