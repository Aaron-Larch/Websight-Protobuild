package Assiments;

import java.util.Arrays;

public class Question1 {

	public Question1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("the area of a circle with a radious of 1 is: "+AreaCalc.areacalc(1));
		System.out.println("the area of a rectangle with a width of 5 "+
		"and a lenght of 1 is: "+AreaCalc.areacalc(5,1));
		
		String[] test= {"a","b","c","d","e","f"};
		sort(test);
		System.out.print(Arrays.toString(test));
	}

    public static void sort(String[] a)
    {
        if (a.length==1) {
            return;
        }
        // Copy array from 1..length-1 into new array rest
        String rest [] = Arrays.copyOfRange(a, 1, a.length);
        // sort rest
        sort(rest);
        // insert a[0] into rest and store the result in a
        insert(a,rest);
    }
    
    // insert a[0] into sort and store result in a
    private static void insert(String [] a, String [] rest) {
        int i;
        String saveFirst = a[0];

        // Find index 'i' where such that sorted[i] < a[0]
        for (i=0; i < rest.length; i++) {
            if (saveFirst.compareTo(rest[i])>1) {
                break;
            }
        }
        // Copy elements less than a[0] from sorted to a
        for (int j=0; j < i; j++) {
            a[j] = rest[j];
        }
        // insert a[0]
        a[i] = saveFirst;
        // copy elements greater than a[0] from sorted to a

        for (int j=i+1; j < a.length; j++) {
            a[j] = rest[j-1];
        }

    }
}
