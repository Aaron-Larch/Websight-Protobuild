/**
 * 
 */
package Assiments;

/**
 * @author gce
 *
 */
public class ExcDemo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nums[] = new int[4];
		try {
			System.out.println("Before exception is generated");
			//Generate an index out-of-bounds Exception
			nums[7]=10;
			System.out.println("this wont be displayed");
		}catch(ArrayIndexOutOfBoundsException exc) {
			//catch the exception
			System.out.println("Index is out of bounds!");
		}
		System.out.println("after the catch statement");
	}

}
