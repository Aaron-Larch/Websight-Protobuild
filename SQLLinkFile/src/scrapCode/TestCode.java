package scrapCode;

import java.text.DecimalFormat;

public class TestCode {

	public TestCode() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//You need to declare a variable before you use it
	    float f = 9.5f;
	    System.out.println(Math.round(f)); //Output is 9
	    System.out.println(Math.ceil(f)); //Output is 10
	    System.out.println(Math.floor(f)); //Output is 9
		DecimalFormat df = new DecimalFormat("###.###");
		double conversion;
		double Test=234.232935465576;
		//Establish x as a variable and create a loop that increases that value by one
		for (int i = 1; i <= 30; i++) {
			 //to get ft from cm divide cm by 30.48
			conversion=i/30.48;
			// the % operation checks the remainder of a divisor operation
			 if(i%7==0) {
				 System.out.printf("%.5f ft lucky number 7 %n%n", conversion);}
			 else {
				 System.out.println(roundAvoid(conversion, 5));
				 }
		}
		
		int sum =1;
		for (int i = 0 ; i < 10 ; i ++) {
			sum = (int) (sum + Math.pow(sum, i));
			}
			System.out.println("Sum is: " + sum);
			System.out.println("Sum2 is: " + df.format(Test));
	}

	public static double roundAvoid(double value, int places) {
	    double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
}
