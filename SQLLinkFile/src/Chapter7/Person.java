/**
 * 
 */
package Chapter7;

import javaDemo.Statistics;

/**
 * @author gce
 *
 */
public abstract class Person {

	/**
	 * 
	 */
	// Person will include firstname, lastname, dutystation
	String [] info = new String [6];
	double[] test= {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0};
	
	Person (String F, String L, String D ) {
		info[0] = F; info[1] = L; info[2] = D;
	}
	void bob() {System.out.println("The average a array is "+Statistics.average(test));}
	
	void showInfo() {
		bob();
		System.out.println("The information report for this individual is... ");
		for (int i = 0; i < 6; i++) {
			if (info[i]!=null) System.out.print(info[i] + " ");
		}
		System.out.println("\n");
	}
}
