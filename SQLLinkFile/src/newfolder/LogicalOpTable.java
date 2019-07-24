/**
 * 
 */
package newfolder;

/**
 * @author gce
 *
 */
public class LogicalOpTable {

	/**
	 * 
	 */
	public static void LogicalOpTable(boolean goo, boolean foo) {
		// TODO Auto-generated constructor stub
		boolean p, q;
		p=goo; q=foo;
		System.out.print(p+"\t"+q+"\t");
		System.out.print((p&q)+"\t"+(p|q)+"\t");
		System.out.println((p^q)+"\t"+(!p));
	}

	/**
	 * @param args
	 */
	//Try This 2-2: a truth table for the logical operators.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("P\tQ\tAND\tOR\tXOR\tNOT");
		LogicalOpTable(true, true);
		LogicalOpTable(true, false);
		LogicalOpTable(false, true);
		LogicalOpTable(false, false);
		
		for( int i = 0; i < 81; i++) {
			if ( i%3== 0){
			System.out.print(i);
			if(i==33){System.out.print("was Scottie Pippen’s and Larry Bird’s jersey number!");}
			System.out.println(); 
			}
		}
		
		String str2 = new String("one two one");
		int idx1 = str2.indexOf("one");
		int idx2 = str2.lastIndexOf("one");
		String sub1 = str2.substring(4,6);
		System.out.println(idx1 + " " + idx2 + " " + sub1);


	}

}
