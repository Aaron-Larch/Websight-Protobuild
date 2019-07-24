package Chapter7;

public interface Truck{
	
	//return the range
	int range();
	
	//Compute fuel needed for a given distance
	double fuelneeded(int miles);
	
	// Accessory methods for instance variables
	int getPassengers();
	void setPassengers(int p);
	int getFuelcap();
	void setFuelcap(int f);
	int getMpg();
	void setMpg(int m);
}
