package Assiments;

public class AreaCalc {

	public static double areacalc(double radious) {
		double pi =3.14159; //pi is a fixed number
		double area;
		area=pi*(Math.pow(radious, 2));
		return area;
	}
	
	public static double areacalc(double width, double length) {
		double area;
		area=width*length;
		return area;
	}
}
