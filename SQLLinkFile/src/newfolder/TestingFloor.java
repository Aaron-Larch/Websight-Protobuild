package newfolder;

import java.util.Arrays;

public class TestingFloor {
	public static void main(String[] args) {
		String[] test= {"Mean",">=","123"};
		System.out.println(Arrays.toString(test));
		String[] output= new String[3];
		if(test.length==3) {output=test;}
		else {
			output[0]=test[0];
			output[1]=test[1];
			for(int i=2; i<test.length-1; i++) {output[1]+=" "+test[i].toString();}
			output[2]=test[test.length-1];
		}
		System.out.println(Arrays.toString(output));
	}
}
	
	 