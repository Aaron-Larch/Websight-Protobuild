/**
 * 
 */
package scrapCode;

import java.io.IOException;

/**
 * @author gce
 *
 */
public class Assinment1 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	    char ch, answer = 'K';
	    do {
	      System.out.println("I'm thinking of a letter between A and Z.");
	      System.out.println("Can you guess it?: ");
	      //read a letter, but skip cr/lf
	      do {
	        ch = (char) System.in.read(); // get a char
	      } while(ch == '\n' | ch == '\r');
	      if(ch == answer) System.out.println("** RIGHT! **");
	      else {
	        System.out.print("...Sorry, you're ");
	        if(ch < answer) System.out.println("too low");
	        else System.out.println("too high");
	        System.out.println("Try again!\n");
	      }
	    } while(answer != ch);
	  }

}
